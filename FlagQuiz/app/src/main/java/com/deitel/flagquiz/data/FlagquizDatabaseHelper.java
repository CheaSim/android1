// AddressBookDatabaseHelper.java
// SQLiteOpenHelper subclass that defines the app's database
package com.deitel.flagquiz.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.deitel.flagquiz.data.DatabaseDescription.Contact;

public class FlagquizDatabaseHelper extends SQLiteOpenHelper {
   public FlagquizDatabaseHelper(Context context) {
      super(context, "jereh.db", null, 4);
   }
   @Override
   public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table person(personid integer primary key " +
              " autoincrement,name varchar(20),phone varchar(12) null)");
   }
   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
      db.execSQL("drop table person");
      onCreate(db);
   }
}




/**************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 **************************************************************************/
