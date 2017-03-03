#encoding=utf-8
'''
Created on 2016年5月18日

@author: wang
'''
import tushare as ts
import pandas as pd
from pandas.core.series import Series
import MySQLdb as ms
import time
import datetime
import numpy as np
from time import sleep
# 更新表marketlist 实时数据
def updateMarketlist():
    sh=ts.get_realtime_quotes('sh')
    sz=ts.get_realtime_quotes('sz')
    hs300=ts.get_realtime_quotes('hs300')
    sz50=ts.get_realtime_quotes('sz50')
    zxb=ts.get_realtime_quotes('zxb')
    cyb=ts.get_realtime_quotes('cyb')
    conn= ms.connect(host='localhost',port = 3306,user='root', passwd='123456',db ='websql',charset="utf8")
    cur = conn.cursor()
    s=[sh,sz,hs300,sz50,zxb,cyb]
    values=[]
    for x in s:
        data=pd.DataFrame(x)
        data1=Series(list(data['code']))
        data2=Series(list(data['name']))
        data3=Series(list(data['open']))
        data4=Series(list(data['pre_close']))
        data5=Series(list(data['price']))
        data6=Series(list(data['high']))
        data7=Series(list(data['low']))
        data8=Series(list(data['volume']))
        data9=Series(list(data['amount']))
        data10=Series(list(data['time']))    
        values.append((data1[0].encode("utf-8"),data2[0].encode('utf-8'),data3[0].encode("utf-8"),data4[0].encode("utf-8"),data5[0].encode("utf-8"),data6[0].encode("utf-8"),data7[0].encode("utf-8"),data8[0].encode("utf-8"),data9[0].encode("utf-8"),data10[0].encode("utf-8")))
    cur.execute('delete from marketlist')
    cur.executemany('insert into marketlist (code,name,open,pre_close,price,high,low,volume,amount,time) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)',values)
    conn.commit()
    cur.close()
    conn.close()
# 打开服务器时更新数据库中六个大盘历史数据
def updateMarketone():
    conn= ms.connect(host='localhost',port = 3306,user='root', passwd='123456',db ='websql',charset="utf8")
    cur = conn.cursor()
    mocket=['sh','sz','hs300','sz50','zxb','cyb']
    for x in mocket:
        selectstr='select max(date) from '+x
        cur.execute(selectstr)
        stus = cur.fetchall()
        s=''
        for stu in stus:
            s=stu[0]
        s = s + datetime.timedelta(1)
        now=time.strftime("%Y-%m-%d",time.localtime(time.time()))
        t=s.strftime('%Y-%m-%d')
        mocketone=ts.get_hist_data(x,t,now)
        if len(mocketone.index)!=0:
            data=pd.DataFrame(mocketone)
            dateser=Series(list(data.index))
            openser=Series(list(data['open']))
            highser=Series(list(data['high']))
            closeser=Series(list(data['close']))
            lowser=Series(list(data['low']))
            volumeser=Series(list(data['volume']))
            price_changeser=Series(list(data['price_change']))
            p_changeser=Series(list(data['p_change']))
            ma5ser=Series(list(data['ma5']))
            ma10ser=Series(list(data['ma10']))
            ma20ser=Series(list(data['ma20']))
            v_ma5ser=Series(list(data['v_ma5']))
            v_ma10ser=Series(list(data['v_ma10']))
            v_ma20ser=Series(list(data['v_ma20']))
            values=[]
            for i in range(len(dateser)):
                values.append((dateser[i].encode("utf-8"),np.float(openser[i]),np.float(highser[i]),np.float(closeser[i]),np.float(lowser[i]),np.float(volumeser[i]),np.float(price_changeser[i]),np.float(p_changeser[i]),np.float(ma5ser[i]),np.float(ma10ser[i]),np.float(ma20ser[i]),np.float(v_ma5ser[i]),np.float(v_ma10ser[i]),np.float(v_ma20ser[i])))
            insertstr='insert into '+x+' (date,open,high,close,low,volume,price_change,p_change,ma5,ma10,ma20,v_ma5,v_ma10,v_ma20) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
            cur.executemany(insertstr,values)
        conn.commit()
    cur.close()
    conn.close()
# 更新四个stocklist 实时数据
def updateStockslist():
    conn= ms.connect(host='localhost',port = 3306,user='root', passwd='123456',db ='websql',charset="utf8")
    cur = conn.cursor()
    ipath1='上证A股.txt'
    ipath2='上证B股.txt'
    ipath3 ='深证A股.txt'
    ipath4 ='深证B股.txt'
    upath1=unicode(ipath1 , "utf8")
    upath2=unicode(ipath2 , "utf8")
    upath3=unicode(ipath3 , "utf8")
    upath4=unicode(ipath4 , "utf8")
    file1  = open(upath1)
    file2  = open(upath2)
    file3  = open(upath3)
    file4  = open(upath4)
    file=[file1,file2,file3,file4]
    table=['sha','shb','sza','szb']
    try:
        for i in range(len(file)):
            values=[]
            for line in file[i]:
                line=line.strip('\n')
                str=line.split(" ")
                stocksone=ts.get_realtime_quotes(str[0])
                data=pd.DataFrame(stocksone)
                data1=Series(list(data['code']))
                data2=Series(list(data['name']))
                data3=Series(list(data['open']))
                data4=Series(list(data['pre_close']))
                data5=Series(list(data['price']))
                data6=Series(list(data['high']))
                data7=Series(list(data['low']))
                data8=Series(list(data['b1_p']))
                data9=Series(list(data['a1_p']))
                data10=Series(list(data['b2_p']))
                data11=Series(list(data['a2_p']))
                data12=Series(list(data['b3_p']))
                data13=Series(list(data['a3_p']))
                data14=Series(list(data['b4_p']))
                data15=Series(list(data['a4_p']))
                data16=Series(list(data['b5_p']))
                data17=Series(list(data['a5_p']))
                data18=Series(list(data['volume']))
                data19=Series(list(data['amount']))
                data20=Series(list(data['time']))
                values.append((data1[0].encode("utf-8"),data2[0].encode('utf-8'),str[1].decode('gbk').encode('utf-8'),data3[0].encode("utf-8"),data4[0].encode("utf-8"),data5[0].encode("utf-8"),data6[0].encode("utf-8"),data7[0].encode("utf-8"),data8[0].encode("utf-8"),data9[0].encode("utf-8"),data10[0].encode("utf-8"),data11[0].encode("utf-8"),data12[0].encode("utf-8"),data13[0].encode("utf-8"),data14[0].encode("utf-8"),data15[0].encode("utf-8"),data16[0].encode("utf-8"),data17[0].encode("utf-8"),data18[0].encode("utf-8"),data19[0].encode("utf-8"),data20[0].encode("utf-8")))
            s='delete from '+table[i]
            cur.execute(s)
            s='insert into '+table[i]+' (code,name,industry,open,pre_close,price,high,low,b1_p,a1_p,b2_p,a2_p,b3_p,a3_p,b4_p,a4_p,b5_p,a5_p,volume,amount,time) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
            cur.executemany(s ,values)
    finally:
        conn.commit()
        cur.close()
        conn.close()
        file1 .close( )    
        file2 .close( )  
        file3 .close( )  
        file4 .close( )  
# 打开服务器时更新数据库中160支股票历史数据
def updateStocksone():
    conn= ms.connect(host='localhost',port = 3306,user='root', passwd='123456',db ='websql',charset="utf8")
    cur = conn.cursor()
    ipath1='上证A股.txt'
    ipath2='上证B股.txt'
    ipath3 ='深证A股.txt'
    ipath4 ='深证B股.txt'
    upath1=unicode(ipath1 , "utf8")
    upath2=unicode(ipath2 , "utf8")
    upath3=unicode(ipath3 , "utf8")
    upath4=unicode(ipath4 , "utf8")
    file1  = open(upath1)
    file2  = open(upath2)
    file3  = open(upath3)
    file4  = open(upath4)
    file=[file1,file2,file3,file4]
    try:
        for i in range(len(file)):
            values=[]
            for line in file[i]:
                line=line.strip('\n')
                str=line.split(" ")
                selectstr='select max(date) from s'+str[0]
                cur.execute(selectstr)
                stus = cur.fetchall()
                s=''
                for stu in stus:
                    s=stu[0]
                s = s + datetime.timedelta(1)
                now=time.strftime("%Y-%m-%d",time.localtime(time.time()))
                t=s.strftime('%Y-%m-%d')
                stocksone=ts.get_hist_data(str[0],t,now)
                if len(stocksone.index)!=0:                   
                    data=pd.DataFrame(stocksone)
                    dateser=Series(list(data.index))
                    openser=Series(list(data['open']))
                    highser=Series(list(data['high']))
                    closeser=Series(list(data['close']))
                    lowser=Series(list(data['low']))
                    volumeser=Series(list(data['volume']))
                    price_changeser=Series(list(data['price_change']))
                    p_changeser=Series(list(data['p_change']))
                    ma5ser=Series(list(data['ma5']))
                    ma10ser=Series(list(data['ma10']))
                    ma20ser=Series(list(data['ma20']))
                    v_ma5ser=Series(list(data['v_ma5']))
                    v_ma10ser=Series(list(data['v_ma10']))
                    v_ma20ser=Series(list(data['v_ma20']))
                    turnoverser=Series(list(data['turnover']))
                    values=[]
                    for i in range(len(dateser)):
                        values.append((dateser[i].encode("utf-8"),np.float(openser[i]),np.float(highser[i]),np.float(closeser[i]),np.float(lowser[i]),np.float(volumeser[i]),np.float(price_changeser[i]),np.float(p_changeser[i]),np.float(ma5ser[i]),np.float(ma10ser[i]),np.float(ma20ser[i]),np.float(v_ma5ser[i]),np.float(v_ma10ser[i]),np.float(v_ma20ser[i]),np.float(turnoverser[i])))
                    insertstr='insert into s'+str[0]+' (date,open,high,close,low,volume,price_change,p_change,ma5,ma10,ma20,v_ma5,v_ma10,v_ma20,turnover) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)'
                    cur.executemany(insertstr,values)
                    conn.commit()
    finally:
        cur.close()
        conn.close()
        file1 .close( )    
        file2 .close( )  
        file3 .close( )  
        file4 .close( )   
if __name__ == '__main__':
    print "Start : %s" % time.ctime()
    updateMarketone()
    print "updateMarketone : %s" % time.ctime()
    updateStocksone()
    print "updateStocksone : %s" % time.ctime()
    while 1:        
        updateMarketlist()
        updateStockslist()
        print "updatelist : %s" % time.ctime()
        print "sleep 30 seconds"
        sleep(30)
    pass