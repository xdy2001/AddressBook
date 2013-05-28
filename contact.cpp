#include "contact.h"

Contact::Contact()
{
}

Contact::Contact(const Contact& c)
{
    this->address=c.address;
    this->name=c.name;
    this->number=c.number;
}

bool Contact::isContact(const string &s)
{
    if(s.compare(this->address)==0)    
        return true;
    else if(s.compare(this->name)==0)
        return true;
    else if(s.compare(this->number)==0)
        return true;

    return false;
}

Contact::~Contact()
{
}
