/*
 * Copyright ©2019 Hal Perkins.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Spring Quarter 2019 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

package marvel;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

/** Parser utility to load the Marvel Comics dataset. */
public class MarvelParser {

  /**
   * Reads the Marvel Universe dataset. Each line of the input file contains a character name and a
   * comic book the character appeared in, separated by a tab character
   *
   * @spec.requires filename is a valid file path
   * @param filename the file that will be read
   * @return map of books and all heroes within each book
   */
  public static Map<String, List<String>> parseData(String filename) {
    try {
      Map<String, List<String>> volumes = new HashMap<>();

      Reader reader = Files.newBufferedReader(Paths.get(filename));

      CsvToBean<MarvelModel> csvToBean = new CsvToBeanBuilder<MarvelModel>(reader)
              .withType(MarvelModel.class)
              .withIgnoreLeadingWhiteSpace(true)
              .withSeparator('\t')
              .build();

      Iterator<MarvelModel> csvMarvelIterator = csvToBean.iterator();

      while (csvMarvelIterator.hasNext()) {
        MarvelModel marvelRecord = csvMarvelIterator.next();
        String hero = marvelRecord.getHero();
        String book = marvelRecord.getBook();
        if (!volumes.containsKey(book)) {
          volumes.put(book, new ArrayList<>());
        }
        volumes.get(book).add(hero);
      }

      return volumes;
    } catch (NoSuchFileException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
