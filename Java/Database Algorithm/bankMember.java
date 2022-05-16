/*
 *   Funzione per listare i membri di una banca virtuale in local host da due diverse tabelle.
 *   Tabella users contenente id, username e password; 
 *   Tabella stats che contiene un campo id (unico), un campo user_id che è la FOREIGN KEY di 
 *   id della tabella users ed un campo chiamato balance per simulare il saldo del conto.
 */

private static void bankMember(Connection conn) {

  // Doppie query per selezionare gli ID degli user che poi saranno passati come parametri nella seconda query
  String query = "SELECT id from users";
  String queryJoin = "SELECT t1.username, t2.balance FROM users AS t1 INNER JOIN stats AS t2 WHERE t1.id = ? AND t2.user_id = ?";
  // Dichiarazione di ResultSet's e variabili che saranno popolate di seguito
  ResultSet idResult = null;
  ResultSet joinResult = null;
  String usernameOnJoin;
  double balanceOnJoin;

  // Primo try per preparare la query e recuperare gli id da mettere in idResult (ResultSet)
  try {

    PreparedStatement preparedStatement = conn.prepareStatement(query);

    idResult = preparedStatement.executeQuery();
    System.out.println("Username - Saldo\n");
    // Finchè trova degli id, allora assegna alla variabile singleID l'id recuperato
    while (idResult.next()) {

      int singleID = idResult.getInt(1);
      // Preparazione della seconda query e assegnazione degli id singoli ai paramenti attesi dalla query (?) 
      PreparedStatement preparedStatementJoin = conn.prepareStatement(queryJoin);
      preparedStatementJoin.setInt(1, singleID);
      preparedStatementJoin.setInt(2, singleID);
      // Esecuzione della seconda query
      joinResult = preparedStatementJoin.executeQuery();
      /* 
       * Finchè trova delle righe, continua assegnando i valori ritornati dai campi "username" e "balance" alle 
       * proprie variabili che verranno stampate di seguito.
       */ 
      while (joinResult.next()) {
        usernameOnJoin = joinResult.getString("username");
        balanceOnJoin = joinResult.getInt("balance");
        System.out.println(usernameOnJoin + " - €" + balanceOnJoin + "\t");

      }
    }
  } catch (SQLException e) {

    e.printStackTrace();

  }
}
