#ifndef CONTACT_H
#define CONTACT_H

#include <iostream>
#include <string>

using namespace std;

class Contact
{
public:
    //copy construct
    Contact(const Contact &c);

    //default construct
    Contact();

    ~Contact();

    //determine whether s is name, number or address of this contact
    bool isContact(const string &s);

    string name;
    string number;
    string address;

};

#endif
