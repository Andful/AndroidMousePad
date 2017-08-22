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