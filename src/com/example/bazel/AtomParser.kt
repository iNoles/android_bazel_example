package com.example.bazel

import android.util.Xml
import io.reactivex.Flowable

import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException

import java.io.InputStream
import java.io.IOException

// We don't use namespaces
private val ns: String? = null

data class Entry(val title: String?, val summary: String?, val link: String?)

@Throws(XmlPullParserException::class, IOException::class)
fun atom_parse(inputStream: InputStream): Flowable<MutableList<Entry>> {
    inputStream.use {
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)
        parser.setInput(it, null)
        parser.nextTag()

        return try {
            Flowable.just(readFeed(parser))
        } catch (xmlpe: XmlPullParserException) {
            Flowable.error<MutableList<Entry>>(xmlpe)
        } catch (ioe: IOException) {
            Flowable.error<MutableList<Entry>>(ioe)
        }
    }
}

@Throws(XmlPullParserException::class, IOException::class)
private fun readFeed(parser: XmlPullParser): MutableList<Entry> {
    val entries = mutableListOf<Entry>()

    parser.require(XmlPullParser.START_TAG, ns, "feed")
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }

        // Starts by looking for the entry tag
        if (parser.name == "entry") {
            entries.add(readEntry(parser))
        } else {
            skip(parser)
        }
    }
    return entries
}

// Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
@Throws(XmlPullParserException::class, IOException::class)
private fun readEntry(parser: XmlPullParser): Entry {
    parser.require(XmlPullParser.START_TAG, ns, "entry")
    var title: String? = null
    var summary: String? = null
    var link: String? = null
    while (parser.next() != XmlPullParser.END_TAG) {
        if (parser.eventType != XmlPullParser.START_TAG) {
            continue
        }
        when (parser.name) {
            "title" -> title = readTitle(parser)
            "summary" -> summary = readSummary(parser)
            "link" -> link = readLink(parser)
            else -> skip(parser)
        }
    }
    return Entry(title, summary, link)
}

// Processes title tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readTitle(parser: XmlPullParser): String {
    parser.require(XmlPullParser.START_TAG, ns, "title")
    val title = readText(parser)
    parser.require(XmlPullParser.END_TAG, ns, "title")
    return title
}

// Processes link tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readLink(parser: XmlPullParser): String {
    var link = ""
    parser.require(XmlPullParser.START_TAG, ns, "link")
    val tag = parser.name
    val relType = parser.getAttributeValue(null, "rel")
    if (tag == "link") {
        if (relType == "alternate") {
            link = parser.getAttributeValue(null, "href")
            parser.nextTag()
        }
    }
    parser.require(XmlPullParser.END_TAG, ns, "link")
    return link
}

// Processes summary tags in the feed.
@Throws(IOException::class, XmlPullParserException::class)
private fun readSummary(parser: XmlPullParser): String {
    parser.require(XmlPullParser.START_TAG, ns, "summary")
    val summary = readText(parser)
    parser.require(XmlPullParser.END_TAG, ns, "summary")
    return summary
}

// For the tags title and summary, extracts their text values.
@Throws(IOException::class, XmlPullParserException::class)
private fun readText(parser: XmlPullParser): String {
    var result = ""
    if (parser.next() == XmlPullParser.TEXT) {
        result = parser.text
        parser.nextTag()
    }
    return result
}

@Throws(XmlPullParserException::class, IOException::class)
private fun skip(parser: XmlPullParser) {
    if (parser.eventType != XmlPullParser.START_TAG) {
        throw IllegalStateException()
    }
    var depth = 1
    while (depth != 0) {
        when (parser.next()) {
            XmlPullParser.END_TAG -> depth--
            XmlPullParser.START_TAG -> depth++
        }
    }
}