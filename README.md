# Help with MongoDb

   ## Run mongoDb local server:
   - sudo mongod --config /etc/mongod.conf
   
   ## Drop db in mongoDb: 
   1. in mongosh:
       > use mydb;
       > db.dropDatabase();
   2. or:
   - mongosh --eval "use <dbname>" --eval  "db.dropDatabase()"
   
   ## Drop table:
   1. in mongosh:
       > db.mycollection.drop()
   2. or use '--eval' like earlier.
