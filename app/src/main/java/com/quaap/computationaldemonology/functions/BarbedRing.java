package com.quaap.computationaldemonology.functions;

import android.content.Context;
import android.graphics.Canvas;

import com.quaap.computationaldemonology.util.Rand;

/**
 * Created by tom on 12/6/16.
 *
 *    Copyright (C) 2016   Tom Kliethermes
 *
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 */

public class BarbedRing extends Ring {

    private double size = 2;
    private double speed = 40;
    private double modsize = size;
    private double dsize = .1;

    public BarbedRing(Context context) {
        super(context);
    }

    public void doDraw(final Canvas canvas, final long ticks) {
        super.doDraw(canvas,ticks);

        if (mTouchDY!=0) {
            size+= Math.signum(mTouchDY)/5;
            if (size>15) size=15;
            if (size<1) size=1;
            modsize = size;
            dsize = .1;
        }

        modsize += dsize + (mMoveX + mMoveY)/40;
        dsize += (size - modsize)/size/10;


        if (mTouchDX!=0) {
            speed+= Math.signum(mTouchDX)/5;
            if (speed>60) speed=60;
            if (speed<4) speed=4;
        }

        speed += mMoveZ/10;

        rad = 0;
        do {


            float x = (float) (r * Math.sin(rad));
            float y = (float) (r * Math.cos(rad));
            double rnd = Rand.getDouble();
            int sizex = (int) (modsize*rnd * r / 10 * Math.cos(rad * speed)) + 1;
            int sizey = (int) (modsize*rnd * r / 10 * Math.sin(rad * speed)) + 1;
            canvas.drawLine(mCenterX + x, mCenterY + y, mCenterX + x + sizex, mCenterY + y + sizey, getRandomForeground());


            rad += .005;
        } while (rad<Math.PI*2);

    }

}