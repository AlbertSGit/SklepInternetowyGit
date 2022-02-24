package com.codecool.Pakiety.Ogolny;

import com.codecool.Pakiety.Elementy.UserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skleponline.kod.Pakiety.Elementy.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest<LoginController> {

    private List<User> fakeCustomerUsers = new ArrayList<>();
    private List<User> fakeAdminUsers = new ArrayList<>();


    @BeforeEach
    public void initUsersList(){
        fakeCustomerUsers.add(new User(1,"Kamil1","Kamil1","CUSTOMER"));
        fakeAdminUsers.add(new User(1,"Liz1","Liz1","ADMIN"));
    }




}