package JavaSwing_TicTacToe;

public class Mechanic{
    static String[][] table = new String[3][3];
    static void pinSymbol(String playerInput){
        int mainIndex = Integer.parseInt(playerInput.substring(0, 1));
        int i_Index = mainIndex / 3;
        int j_index = 0;

        String playerSymbol = playerInput.substring(1, 2);

        if(i_Index == 0){
            j_index = mainIndex;
        }
        else if(i_Index == 1){
            j_index = mainIndex - 3;
        }
        else if(i_Index == 2){
            j_index = mainIndex - 6;
        }

        table[i_Index][j_index] = playerSymbol;
    }
    private static boolean isWin = false;
    public static String playerWin = "";
    public static boolean checkWin(){
        isWin = false;
        StringBuilder cmbCol, cmbRow, cmbD1, cmbD2;

        cmbD1 = new StringBuilder();
        cmbD2 = new StringBuilder();

        for (int i = 0; i < 3; i++){
            cmbCol = new StringBuilder();
            cmbRow = new StringBuilder();

            for (int j = 0; j < 3; j++) {
                cmbRow.append(table[i][j]);
                cmbCol.append(table[j][i]);

                if (cmbCol.toString().equals("XXX") || cmbRow.toString().equals("XXX")){
                    playerWin = "Player 'x'";
                    isWin = true;
                } else if (cmbCol.toString().equals("OOO") || cmbRow.toString().equals("OOO")) {
                    playerWin = "Player 'O'";
                    isWin = true;
                }
            }

            cmbD1.append(table[i][i]);
            cmbD2.append(table[i][2 - i]);

            if (cmbD1.toString().equals("XXX") || cmbD2.toString().equals("XXX")){
                playerWin = "Player 'x'";
                isWin = true;
            }
            else if (cmbD1.toString().equals("OOO") || cmbD2.toString().equals("OOO")){
                playerWin = "Player 'O'";
                isWin = true;
            }

        }
        // System.out.println(isWin);
        return isWin;

    }

}
