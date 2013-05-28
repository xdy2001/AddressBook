#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>
#include "cc.h"

//define file of communication
#define UNIX_DOMAIN "/tmp/UNIX.domain"

//file desciptor
int connect_fd;

int Initial()
{
    //start server
    system("./server &");
    int ret;
    //save informationg if server addr
    struct sockaddr_un srv_addr;

    //create socket of unix domain
    connect_fd=socket(PF_UNIX,SOCK_STREAM,0);
    if(connect_fd<0)
    {
        perror("cannot create communication socket");
        return 1;
    }

    srv_addr.sun_family=AF_UNIX;
    strcpy(srv_addr.sun_path,UNIX_DOMAIN);

    //connect server
    ret=connect(connect_fd,(struct sockaddr*)&srv_addr,sizeof(srv_addr));
    if(ret==-1)
    {
        printf("cannot connect to the server\n");
        error("cannot connect to the server");
        close(connect_fd);
        return 1;
    }
}

//send msg to server
void Send(char *msg,size_t size)
{   
    write(connect_fd,msg,size);
}

//receive msg from server
void Receive(char *msg,size_t size)
{   
    read(connect_fd,msg,size);
}

void EndSesstion()
{
    close(connect_fd);
}



