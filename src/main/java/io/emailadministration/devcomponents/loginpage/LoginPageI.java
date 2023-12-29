package io.emailadministration.devcomponents.loginpage;

import io.emailadministration.devcomponents.header.IHeader;
import io.emailadministration.devcomponents.menu.usingmenu.IMenu;

public interface LoginPageI {
    IMenu generateLoginOrSignUpPage();
    IHeader extractHeader();
}
