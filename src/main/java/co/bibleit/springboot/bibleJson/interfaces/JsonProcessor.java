package co.bibleit.springboot.bibleJson.interfaces;

import co.bibleit.springboot.bible.interfaces.Chapter;
import co.bibleit.springboot.bible.interfaces.ScriptureCollection;

import java.util.List;
import java.util.Map;

public interface JsonProcessor {
    void setupScriptureCollection();

    List<String> getBookStringNames();

    Map<String, Chapter> getBook(String bibleBook);

    Chapter getChapter(Map<String, Chapter> book, String chapter);

    ScriptureCollection getBible();


}