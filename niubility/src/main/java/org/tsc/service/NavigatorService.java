package org.tsc.service;

import org.tsc.model.Navigator;
import org.tsc.model.Recipient;

import java.util.List;

public interface NavigatorService {
    List<Navigator> getRecommendedNavigators(Recipient recipient);
}
