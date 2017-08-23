#include "mouseFunction.h"

#if defined(_WIN32) || defined(_WIN64)
void moveMouse(int x,int y) {
	POINT point;
	if(GetCursorPos(&point)) {
		SetCursorPos(point.x+x,point.y+y);
	}
}

void setMouse(int x,int y) {
	SetCursorPos(x,y);
}

#endif
#ifdef __linux__
#include <iostream>
void moveMouse(int x,int y) {
	Display *dpy;
	Window root_window;

	dpy = XOpenDisplay(0);
	root_window = XRootWindow(dpy, 0);
	XSelectInput(dpy, root_window, KeyReleaseMask);
	XWarpPointer(dpy, None, root_window, 0, 0, 0, 0, 100, 100);
	XFlush(dpy);
	//NOT WORKING
}

void setMouse(int x,int y){
	//TODO
}

#endif
