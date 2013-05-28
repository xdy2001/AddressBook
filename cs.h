#ifndef CS_H
#define CS_H

#ifdef __cplusplus
extern "C" {
#endif

//create c function that c++ can call
int Initial();
void Accept(char *msg,size_t size);
void Response(char *msg,size_t size);
void EndSesstion();

#ifdef __cplusplus
}
#endif

#endif
