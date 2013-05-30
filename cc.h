#ifndef CC_H
#define CC_H

#ifdef __cplusplus
extern "C" {
#endif

int Initial();
void Send(char *msg,size_t size);
void Receive(char *msg,size_t size);
void EndSesstion();
#ifdef __cplusplus
}
#endif

#endif
