package org.tsc.service;

import java.util.List;
import java.util.Map;

public interface TSCTrackService {

    Map<String, String> opiateStatistics();

    List<Map<String, String>> caseManagementStatistics();

    Map<String, String> programStatistics();

    Map<String, String> demographicStatistics();
}
