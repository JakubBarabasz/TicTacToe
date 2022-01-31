package com.example.ticaitoe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.GridLayout.LayoutParams;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import java.util.HashMap;



public final class MainActivity extends AppCompatActivity {
        private final ImageView[][] boardCells;
        private Board board;
        private HashMap _$_findViewCache;

        public final Board getBoard() {
                return this.board;
        }

        public final void setBoard(Board var1) {
                this.board = var1;
        }

        protected void onCreate(Bundle  savedInstanceState) {
                super.onCreate(savedInstanceState);
                this.loadBoard();

        }
        public void exit(View view) {
                TextView back = findViewById(R.id.back);
                System.exit(0);

        }
        public void restart(View view) {
                TextView back = findViewById(R.id.button_restart);
                mapBoardToUi();

        }

        private final void mapBoardToUi() {
                int i = 0;

                for(int var2 = this.board.getBoard().length; i < var2; ++i) {
                        int j = 0;

                        for(int var4 = this.board.getBoard().length; j < var4; ++j) {
                                String var10000 = this.board.getBoard()[i][j];
                                ImageView var6;
                                if (var10000 != null) {
                                        String var5 = var10000;
                                        switch(var5.hashCode()) {
                                                case 79:
                                                        if (var5.equals("O")) {
                                                                var6 = this.boardCells[i][j];
                                                                if (var6 != null) {
                                                                        var6.setImageResource(R.drawable.circle);
                                                                }

                                                                var6 = this.boardCells[i][j];
                                                                if (var6 != null) {
                                                                        var6.setEnabled(false);
                                                                }
                                                                continue;
                                                        }
                                                        break;
                                                case 88:
                                                        if (var5.equals("X")) {
                                                                var6 = this.boardCells[i][j];
                                                                if (var6 != null) {
                                                                        var6.setImageResource(R.drawable.cross);
                                                                }

                                                                var6 = this.boardCells[i][j];
                                                                if (var6 != null) {
                                                                        var6.setEnabled(false);
                                                                }
                                                                continue;
                                                        }
                                        }
                                }

                                var6 = this.boardCells[i][j];
                                if (var6 != null) {
                                        var6.setImageResource(0);
                                }

                                var6 = this.boardCells[i][j];
                                if (var6 != null) {
                                        var6.setEnabled(true);
                                }
                        }
                }

        }

        private final void loadBoard() {
                int i = 0;

                for(int var2 = this.boardCells.length; i < var2; ++i) {
                        int j = 0;

                        for(int var4 = this.boardCells.length; j < var4; ++j) {
                                this.boardCells[i][j] = new ImageView((Context)this);
                                ImageView var10000 = this.boardCells[i][j];
                                if (var10000 != null) {
                                        LayoutParams var5 = new LayoutParams();
                                        ImageView var8 = var10000;
                                        var5.rowSpec = GridLayout.spec(i);
                                        var5.columnSpec = GridLayout.spec(j);
                                        var5.width = 250;
                                        var5.height = 250;
                                        var5.bottomMargin = 5;
                                        var5.topMargin = 5;
                                        var5.leftMargin = 5;
                                        var5.rightMargin = 5;
                                        var8.setLayoutParams((android.view.ViewGroup.LayoutParams)var5);
                                }

                                var10000 = this.boardCells[i][j];
                                if (var10000 != null) {
                                        var10000.setBackgroundColor(ContextCompat.getColor((Context)this, R.color.colorPrimary));
                                }

                                var10000 = this.boardCells[i][j];
                                if (var10000 != null) {
                                        var10000.setOnClickListener((OnClickListener)(new MainActivity.CellClickListener(i, j)));
                                }

                                ((GridLayout)this.findViewById(R.id.layout_board)).addView((View)this.boardCells[i][j]);
                        }
                }

        }

        public MainActivity() {
                byte var1 = 3;
                ImageView[][] var2 = new ImageView[var1][];

                for(int var3 = 0; var3 < var1; ++var3) {
                        ImageView[] var9 = new ImageView[3];
                        var2[var3] = var9;
                }

                this.boardCells = (ImageView[][])var2;
                this.board = new Board();
        }

        public View _$_findCachedViewById(int var1) {
                if (this._$_findViewCache == null) {
                        this._$_findViewCache = new HashMap();
                }

                View var2 = (View)this._$_findViewCache.get(var1);
                if (var2 == null) {
                        var2 = this.findViewById(var1);
                        this._$_findViewCache.put(var1, var2);
                }

                return var2;
        }

        public void _$_clearFindViewByIdCache() {
                if (this._$_findViewCache != null) {
                        this._$_findViewCache.clear();
                }

        }



        public final class CellClickListener implements OnClickListener {
                private final int i;
                private final int j;

                public void onClick( View p0) {
                        if (!MainActivity.this.getBoard().isGameOver()) {
                                Cell cell = new Cell(this.i, this.j);
                                MainActivity.this.getBoard().placeMove(cell, "O");
                                MainActivity.this.getBoard().minimax(0, "X");
                                Cell var10000 = MainActivity.this.getBoard().getComputersMove();
                                if (var10000 != null) {
                                        Cell var3 = var10000;

                                        MainActivity.this.getBoard().placeMove(var3, "X");
                                }

                                MainActivity.this.mapBoardToUi();
                        }

                        TextView var6;
                        if (MainActivity.this.getBoard().hasComputerWon()) {
                                var6 = (TextView)MainActivity.this.findViewById(R.id.text_view_result);
                                var6.setText((CharSequence)"Robot Won");
                        } else if (MainActivity.this.getBoard().hasPlayerWon()) {
                                var6 = (TextView)MainActivity.this.findViewById(R.id.text_view_result);
                                var6.setText((CharSequence)"You Won");
                        } else if (MainActivity.this.getBoard().isGameOver()) {
                                var6 = (TextView)MainActivity.this.findViewById(R.id.text_view_result);
                                var6.setText((CharSequence)"Game Tied");
                        }

                }

                public CellClickListener(int i, int j) {
                        this.i = i;
                        this.j = j;
                }
        }
}
