// AddressBookContentProvider.java
// ContentProvider subclass for manipulating the app's database
package com.deitel.flagquiz.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.deitel.flagquiz.data.FlagquizDatabaseHelper;
import com.deitel.flagquiz.R;
import com.deitel.flagquiz.data.DatabaseDescription.Contact;

public class MyContentProvider extends ContentProvider {

   private FlagquizDatabaseHelper openHelper;
   private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
   private static final int PERSONS = 1;
   private static final int PERSON = 2;
   static{
      MATCHER.addURI("com.deitel.flagquiz.data.MyContentProvider", "person", PERSONS);
      //* 根据pesonid来删除记录
      MATCHER.addURI("com.deitel.flagquiz.data.MyContentProvider", "person/#", PERSON);
   }
   @Override
   public boolean onCreate() {
      openHelper = new FlagquizDatabaseHelper(this.getContext());
      return false;
   }
   @Override
   public Cursor query(Uri uri, String[] projection, String selection,
                       String[] selectionArgs, String sortOrder) {
      SQLiteDatabase sqLiteDatabase = openHelper.getReadableDatabase();
      switch (MATCHER.match(uri)) {
         case 1:
            return sqLiteDatabase.query("person", projection, selection, selectionArgs, null, null, sortOrder);
         case 2:
            long rowid = ContentUris.parseId(uri);
            String where = "personid="+rowid;
            if(selection != null && "".equals(selection.trim())){
               where = selection+"and"+where;
            }
            return sqLiteDatabase.query("person", projection, where, selectionArgs, null, null, sortOrder);
      }
      return null;
   }

   @Override
   public String getType(Uri uri) {
      switch (MATCHER.match(uri)) {
         case 1:
            return "vnd.android.cursor.dir/person";
         case 2:
            return "vnd.android.cursor.item/person";
      }
      return null;
   }

   @Override
   public Uri insert(Uri uri, ContentValues values) {
      SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
      switch (MATCHER.match(uri)) {
         case 1:
            long rowid = sqLiteDatabase.insert("person", "name", values);
            return ContentUris.withAppendedId(uri, rowid);

         default:
            break;
      }
      return null;
   }

   //* 删除特定personid行的记录
   @Override
   public int delete(Uri uri, String selection, String[] selectionArgs) {
      SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
      switch (MATCHER.match(uri)) {
         case 1:
            return sqLiteDatabase.delete("person", selection, selectionArgs);
         case 2:
            long rowid = ContentUris.parseId(uri);
            String where = "personid="+rowid;
            if(selection != null && "".equals(selection.trim())){
               where = selection+"and"+where;
            }
            return sqLiteDatabase.delete("person", where, selectionArgs);
      }
      return 0;
   }

   @Override
   public int update(Uri uri, ContentValues values, String selection,
                     String[] selectionArgs) {
      SQLiteDatabase sqLiteDatabase = openHelper.getWritableDatabase();
      switch (MATCHER.match(uri)) {
         case 1:
            return sqLiteDatabase.update("person", values, selection, selectionArgs);
         case 2:
            long rowid = ContentUris.parseId(uri);
            String where = "personid="+rowid;
            if(selection != null && "".equals(selection.trim())){
               where = selection+"and"+where;
            }
            return sqLiteDatabase.update("person", values, where, selectionArgs);
      }
      return 0;
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
