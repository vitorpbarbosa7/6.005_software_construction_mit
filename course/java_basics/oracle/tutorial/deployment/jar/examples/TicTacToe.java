/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.applet.*;

/**
 * A TicTacToe applet. A very simple, and mostly brain-dead
 * implementation of your favorite game! <p>
 *
 * In this game a position is represented by a white and black
 * bitmask. A bit is set if a position is ocupied. There are
 * 9 squares so there are 1<<9 possible positions for each
 * side. An array of 1<<9 booleans is created, it marks
 * all the winning positions.
 *
 * @version 	1.2, 13 Oct 1995
 * @author Arthur van Hoff
 * @modified 96/04/23 Jim Hagen : winning sounds
 */
public
class TicTacToe extends Applet {
    /**
     * White's current position. The computer is white.
     */
    int white;

    /**
     * Black's current position. The user is black.
     */
    int black;

    /**
     * The squares in order of importance...
     */
    final static int moves[] = {4, 0, 2, 6, 8, 1, 3, 5, 7};

    /**
     * The winning positions.
     */
    static boolean won[] = new boolean[1 << 9];
    static final int DONE = (1 << 9) - 1;
    static final int OK = 0;
    static final int WIN = 1;
    static final int LOSE = 2;
    static final int STALEMATE = 3;

    /**
     * Mark all positions with these bits set as winning.
     */
    static void isWon(int pos) {
	for (int i = 0 ; i < DONE ; i++) {
	    if ((i & pos) == pos) {
		won[i] = true;
	    }
	}
    }

    /**
     * Initialize all winning positions.
     */
    static {
	isWon((1 << 0) | (1 << 1) | (1 << 2));
	isWon((1 << 3) | (1 << 4) | (1 << 5));
	isWon((1 << 6) | (1 << 7) | (1 << 8));
	isWon((1 << 0) | (1 << 3) | (1 << 6));
	isWon((1 << 1) | (1 << 4) | (1 << 7));
	isWon((1 << 2) | (1 << 5) | (1 << 8));
	isWon((1 << 0) | (1 << 4) | (1 << 8));
	isWon((1 << 2) | (1 << 4) | (1 << 6));
    }

    /**
     * Compute the best move for white.
     * @return the square to take
     */
    int bestMove(int white, int black) {
	int bestmove = -1;
	
      loop:
	for (int i = 0 ; i < 9 ; i++) {
	    int mw = moves[i];
	    if (((white & (1 << mw)) == 0) && ((black & (1 << mw)) == 0)) {
		int pw = white | (1 << mw);
		if (won[pw]) {
		    // white wins, take it!
		    return mw;
		}
		for (int mb = 0 ; mb < 9 ; mb++) {
		    if (((pw & (1 << mb)) == 0) && ((black & (1 << mb)) == 0)) {
			int pb = black | (1 << mb);
			if (won[pb]) {
			    // black wins, take another
			    continue loop;
			}
		    }
		}
		// Neither white nor black can win in one move, this will do.
		if (bestmove == -1) {
		    bestmove = mw;
		}
	    }
	}
	if (bestmove != -1) {
	    return bestmove;
	}

	// No move is totally satisfactory, try the first one that is open
	for (int i = 0 ; i < 9 ; i++) {
	    int mw = moves[i];
	    if (((white & (1 << mw)) == 0) && ((black & (1 << mw)) == 0)) {
		return mw;
	    }
	}

	// No more moves
	return -1;
    }

    /**
     * User move.
     * @return true if legal
     */
    boolean yourMove(int m) {
	if ((m < 0) || (m > 8)) {
	    return false;
	}
	if (((black | white) & (1 << m)) != 0) {
	    return false;
	}
	black |= 1 << m;
	return true;
    }

    /**
     * Computer move.
     * @return true if legal
     */
    boolean myMove() {
	if ((black | white) == DONE) {
	    return false;
	}
	int best = bestMove(white, black);
	white |= 1 << best;
	return true;
    }

    /**
     * Figure what the status of the game is.
     */
    int status() {
	if (won[white]) {
	    return WIN;
	}
	if (won[black]) {
	    return LOSE;
	}
	if ((black | white) == DONE) {
	    return STALEMATE;
	}
	return OK;
    }

    /**
     * Who goes first in the next game?
     */
    boolean first = true;

    /**
     * The image for white.
     */
    Image notImage;

    /**
     * The image for black.
     */
    Image crossImage;

    /**
     * Initialize the applet. Resize and load images.
     */
    public void init() {
	notImage = getImage(getClass().getResource("images/not.gif"));
	crossImage = getImage(getClass().getResource("images/cross.gif"));
    }

    /**
     * Paint it.
     */
    public void paint(Graphics g) {
	Dimension d = getSize();
	g.setColor(Color.black);
	int xoff = d.width / 3;
	int yoff = d.height / 3;
	g.drawLine(xoff, 0, xoff, d.height);
	g.drawLine(2*xoff, 0, 2*xoff, d.height);
	g.drawLine(0, yoff, d.width, yoff);
	g.drawLine(0, 2*yoff, d.width, 2*yoff);

	int i = 0;
	for (int r = 0 ; r < 3 ; r++) {
	    for (int c = 0 ; c < 3 ; c++, i++) {
		if ((white & (1 << i)) != 0) {
		    g.drawImage(notImage, c*xoff + 1, r*yoff + 1, this);
		} else if ((black & (1 << i)) != 0) {
		    g.drawImage(crossImage, c*xoff + 1, r*yoff + 1, this);
		}
	    }
	}
    }

    /**
     * The user has clicked in the applet. Figure out where
     * and see if a legal move is possible. If it is a legal
     * move, respond with a legal move (if possible).
     */
    public boolean mouseUp(Event evt, int x, int y) {
	switch (status()) {
	  case WIN:
	  case LOSE:
	  case STALEMATE:
	    play(getClass().getResource("audio/return.au"));
	    white = black = 0;
	    if (first) {
		white |= 1 << (int)(Math.random() * 9);
	    }
	    first = !first;
	    repaint();
	    return true;
	}

	// Figure out the row/colum
	Dimension d = getSize();
	int c = (x * 3) / d.width;
	int r = (y * 3) / d.height;
	if (yourMove(c + r * 3)) {
	    repaint();

	    switch (status()) {
	      case WIN:
		play(getClass().getResource("audio/yahoo1.au"));
		break;
	      case LOSE:
		play(getClass().getResource("audio/yahoo2.au"));
		break;
	      case STALEMATE:
		break;
	      default:
		if (myMove()) {
		    repaint();
		    switch (status()) {
		      case WIN:
			play(getClass().getResource("audio/yahoo1.au"));
			break;
		      case LOSE:
			play(getClass().getResource("audio/yahoo2.au"));
			break;
		      case STALEMATE:
			break;
		      default:
			play(getClass().getResource("audio/ding.au"));
		    }
		} else {
		    play(getClass().getResource("audio/beep.au"));
		}
	    }
	} else {
	    play(getClass().getResource("audio/beep.au"));
	}
	return true;
    }

    public String getAppletInfo() {
	return "TicTacToe by Arthur van Hoff";
    }
}
