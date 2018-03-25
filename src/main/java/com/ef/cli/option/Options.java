package com.ef.cli.option;

import java.util.Map;
import java.util.Optional;

public interface Options {

    void addOption(String opt, String description);

    boolean hasOption(String opt);

    Optional<Map<String, String>> getOptions();
}
