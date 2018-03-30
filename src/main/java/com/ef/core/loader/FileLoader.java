package com.ef.core.loader;

import java.util.List;

public interface FileLoader {

    List<String> readLines(String path);
}
