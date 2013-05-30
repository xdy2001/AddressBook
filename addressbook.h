#ifndef ADDRESSBOOK_H
#define ADDRESSBOOK_H

#include <iostream>
#include <fstream>
#include <list>
#include <string>
#include "contact.h"

using namespace std;

typedef list<Contact>::iterator pList;

class AddressBook
{
public:
    AddressBook();

    //add contact c to contact
    //if c has existed at contact, giving tip
    bool add(const Contact &c);

    //search s from range of [b,e)
    //if exist, return position it first appear
    //else return e
    //s is contact's name, number or address
    pList search(pList b,pList e,const string &s);

    //search from contact,
    //if exist, return true and
    //place position it first appear to r
    //else return false
    //s is contact's name, number or address
    bool search(const string &s,pList &r);

    //delete all s of contact,
    //s is contact's name, number or address
    bool remove(const string &s);

    //show help
    bool help();

    //save contact to file, when exiting
    bool save(list<Contact> c);

    //list saving contaxts
    list<Contact> contact;

    //mark whether quiting
    bool isQuit;

    //destructor
    ~AddressBook();
};

#endif
