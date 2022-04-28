package top.mnsx.foreground.service;

import javax.servlet.http.HttpSession;

public interface KeyService {
    Boolean useKey(String keyCode, Long curId);
}
