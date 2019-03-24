import cx_Oracle as db
con  = db.connect('SOCCER02/howdy@127.0.0.1/orcl')
print(con.version)
con.close()
