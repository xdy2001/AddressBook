#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>

//define file of communication
#define UNIX_DOMAIN "/tmp/UNIX.domain"

//file descriptor
int listen_fd;
int com_fd;

int Initial()
{
    int ret;
    int len;
    struct sockaddr_un clt_addr;
    struct sockaddr_un srv_addr;

    //create socket
    listen_fd=socket(PF_UNIX,SOCK_STREAM,0);
    if(listen_fd<0)
    {
        perror("cannot create listening socket");
        return 1;
    }

    //configure server address information
    srv_addr.sun_family=AF_UNIX;
    strncpy(srv_addr.sun_path,UNIX_DOMAIN,sizeof(srv_addr.sun_path)-1);
    unlink(UNIX_DOMAIN);

    //bind socket and server address information
    ret=bind(listen_fd,(struct sockaddr*)&srv_addr,sizeof(srv_addr));
    if(ret==-1)
    {
        perror("cannot bind server socket");
        close(listen_fd);
        unlink(UNIX_DOMAIN);
        return 1;
    }

    //对listen socket
    ret=listen(listen_fd,1);
    if(ret==-1)
    {
        perror("cannot listen the client connect request");
        close(listen_fd);
        unlink(UNIX_DOMAIN);
        return 1;
    }


    //call accept when there is connect request
    len=sizeof(clt_addr);
    com_fd=accept(listen_fd,(struct sockaddr*)&clt_addr,&len);
    if(com_fd<0)
    {
        perror("cannot accept client connect request");
        close(listen_fd);
        unlink(UNIX_DOMAIN);
        return 1;
    }

}



//accept msg from client
void Accept(char *msg)
{
    memset(msg,0,1024);
    read(com_fd,msg,1024);
}

//send response msg to client
void Response(char *msg)
{
    write(com_fd,msg,1024);
}
void EndSesstion()
{
    close(com_fd);
    close(listen_fd);
    unlink(UNIX_DOMAIN);
}

