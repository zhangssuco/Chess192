package com.example.chess19;

// data model
class chess {
    char[][] game;
    int step;
    boolean gameover = false;
    static final char EMPTY = '-';
    static final char LIGHTPAWN = 'p';
    public static final char DARKPAWN = 'P';
    public static final char LIGHTROOK = 'r';
    public static final char DARKROOK = 'R';
    char LIGHTKNIGHT = 'n';
    char DARKKNIGHT = 'N';
    char LIGHTBISHOP = 'b';
    char DARKBISHOP = 'B';
    char LIGHTQUEEN = 'q';
    char DARKQUEEN = 'Q';
    char LIGHTKING = 'k';
    char DARKKING = 'K';

    public chess() {
        step=0;
        game = new char[8][8];

        game[7][0] = game[7][7] = LIGHTROOK;  //'r';
        game[0][0] = game[0][7] = DARKROOK; //'R'

        game[7][2] = game[7][5] = LIGHTBISHOP;  //'r';
        game[7][4] = LIGHTKING;  //'r';
        //!!! Expand this part
        // you need to do fill in  blank

        game[7][3] = LIGHTQUEEN;
        game[0][3] = DARKQUEEN;
        game[0][4] = DARKKING;

        for (int c = 0; c < 8; c++) {
            game[6][c] = LIGHTPAWN;
            // you need to figure out how to initialize dark PAWN
        }

        for (int r = 2; r <= 5; r++)
            for (int c = 0; c < 8; c++)
                game[r][c] = EMPTY;

    }


    void display()
    {
        for (int r = 0; r <8; r++)
        {
            for (int c = 0; c < 8; c++)
                System.out.print(game[r][c]);
            System.out.println();
        }


    }

    int whichside(char p)
    {
        if (Character.isUpperCase(p)) return 1; // dark side
        if (Character.isLowerCase(p)) return 0; // light side
        else return -1; // empty
    }
    /*
    check if a piece is lower case or not
    */

    boolean islightside(int row, int col)
    {

        boolean checkBool = Character.isLowerCase(game[row][col]);
        return checkBool;
    }

    //!!! you might want to use this signature, correspondingly change the way the function is called!
    //boolean makeamove(int srow,int scol, int drow, int  dcol)
    void makeamove(int srow,int scol, int drow, int  dcol)
    {
        //!!!  Do something here!

        // you should return immediately if index are out of boundary!
        //  also the character at the source position should not be EMPTY, should be consistent with step
        //  try to use step%2 and the above whichside function to check whether it is moving a valid side.
        /*
            More hints: We use a step counter, which is initialized to be zero and keeps track number of moves.
            Encoded all dark pieces using upper case letters , and light lower.
            Therefore, before making a move, if the step counter is odd, and the source piece is light, then return. If it is even, and .... then return.
            Basically, by checking the current step counter and upper case or lower case of the source piece, you can reject a move or continue to make the move.
        */
        if (step%2==1 && islightside(srow, scol)) return;

        System.out.println(srow+","+scol+","+drow+","+dcol);
        game[drow][dcol] = game[srow][scol];
        game[srow][scol] = EMPTY;
        step++; // counting on how many steps.
    }
}
