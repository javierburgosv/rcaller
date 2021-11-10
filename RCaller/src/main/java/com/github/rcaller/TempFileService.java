/*
 *
RCaller, A solution for calling R from Java
Copyright (C) 2010-2014  Mehmet Hakan Satman

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 * Mehmet Hakan Satman - mhsatman@yahoo.com
 * http://www.mhsatman.com
 * Google code project: https://github.com/jbytecode/rcaller
 * Please visit the blog page with rcaller label:
 * http://stdioe.blogspot.com.tr/search/label/rcaller
 */

package com.github.rcaller;

import com.github.rcaller.exception.ExecutionException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TempFileService {

    private static final Logger logger = Logger.getLogger(TempFileService.class.getName());

    private ArrayList<File> tempFiles;
    private File tempDirectory;

    public TempFileService() {
        tempFiles = new ArrayList<>();
    }

    public TempFileService(String tempDirectory) {
        this();
        this.tempDirectory = new File(tempDirectory);
    }

    public File createTempFile(String prefix, String suffix) throws IOException {
        File f = tempFiles != null ? File.createTempFile(prefix, suffix, tempDirectory)
                : File.createTempFile(prefix, suffix);
        tempFiles.add(f);
        return (f);
    }

    public void deleteRCallerTempFiles() {
        for (File tempFile : tempFiles) {
            if (!tempFile.delete()) {
                logger.log(Level.WARNING, "Couldn't delete file ".concat(tempFile.getName()));
            }
        }
        tempFiles.clear();
    }

    public File createOutputFile() {
        try {
            return createTempFile("ROutput", "");
        } catch (Exception e) {
            throw new ExecutionException(
                    "Can not create a temporary file for storing the R results: " + e.getMessage());
        }
    }

    public File createControlFile() {
        try {
            return createTempFile("RControl", "");
        } catch (Exception e) {
            throw new ExecutionException(
                    "Can not create a temporary file for storing the R results: " + e.getMessage());
        }
    }
}
