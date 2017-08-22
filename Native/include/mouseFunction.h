#ifndef MOUSE_FUNCTION
#define MOUSE_FUNCTION

#if defined(_WIN32) || defined(_WIN64)
#include <Windows.h>
#endif // Win

#ifdef __linux__

#endif

void moveMouse(int x,int y);
void setMouse(int x,int y);


#endif // !MOUSE_FUNCTION