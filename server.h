#ifndef SERVER_H
#define SERVER_H

#include <string>
#include <fstream>
#include <iostream>
#include "addressbook.h"

using namespace std;

class Server
{
public:
    //default constructor
    Server();

    //destructor
    ~Server();

    //accept message from client and save to msg
    void accept();

    //respond to client and save response content to msg
    void response();

    //save message from communication of client and sever
    char msg[1024];

    //convert string to char
    void stringTochar(const string &s);

    //addressbook
    AddressBook ad;
};
#endif
