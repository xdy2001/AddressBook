#include "addressbook.h"

AddressBook::AddressBook():isQuit(0)
{
    char buf[256]="";
    Contact c;

    ifstream ifs;

    ifs.exceptions (ifstream::failbit | ifstream::badbit );
    try
    {
        ifs.open("contact.txt",ifstream::in);
        while(ifs.peek()!=EOF)
        {
            ifs.getline(buf,255);
            c.name.append(buf);
            ifs.getline(buf,255);
            c.number.append(buf);
            ifs.getline(buf,255);
            c.address.append(buf);

            contact.push_back(c);
            c.name.clear();
            c.number.clear();
            c.address.clear();
        }
        ifs.close();
    }
    catch(std::ifstream::failure e)
    {
        cerr << "Exception opening/reading/closing file\n";
        cerr<<"The file contact.txt maybe don't exit or have been destroyed\n";
        cerr<<"Please try to create file contact.txt again\n";
    }

}

bool AddressBook::add(const Contact &c)
{
    pList it;

    //chech if the person have exited
    if(search(c.name,it))
        return false;
    else
    {
        contact.push_back(c);
        return true;
    }

}

bool AddressBook::help()
{
    cout<<"Input add----\tfor adding contact"<<endl;
    cout<<"for example"<<endl;
    cout<<"name: xiaoming"<<endl;
    cout<<"mobile: 18888888888"<<endl;
    cout<<"address: xiaoming jia"<<endl;
    cout<<"address entry added"<<endl;
    cout<<"Input search----\tfor searching contact"<<endl;
    cout<<"Input delete----\tfor deleting contact"<<endl;
    cout<<"Input !help----\tfor help"<<endl;
    cout<<"Input !quit----\tfor quit"<<endl;
    return true;
}

bool AddressBook::remove(const string &s)
{
    pList it;

    if(search(s,it))
    {
        pList it0=contact.end();
        while(it!=it0)
        {
            it=contact.erase(it);
            it=search(it,it0,s);
        }
    }
    else
        return false;
    return true;
}

bool AddressBook::save(list<Contact> c)
{
    Contact temp;
    ofstream out("contact.txt",ofstream::trunc);
    while(!contact.empty())
    {
        temp=contact.front();
        out<<temp.name<<"\n";
        out<<temp.number<<"\n";
        out<<temp.address<<"\n";
        contact.pop_front();
    }
    out.close();
}

bool AddressBook::search(const string &s,pList &r)
{
    list<Contact>::iterator it1,it2;
    if(contact.size()!=0)
    {
        it1=contact.begin();
        it2=contact.end();
        r=search(it1,it2,s);
        if(r==it2)
            return false;
        else
            return true;
    }
    else
        return false;
}

pList AddressBook::search(pList b,pList e,const string &s)
{
    for(;b!=e;b++)
    {
        if(b->isContact(s))
            return b;
    }
    return e;
}

AddressBook::~AddressBook()
{
    save(contact);
}
