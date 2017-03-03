/**
 * 
 */
package BL;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.function.DoubleToLongFunction;

import PO.StockPO;
import PO.ZhiBiaoPO;
import blService.getZhibiaoBlSer;

/**
 * @author lenovo
 *
 */
public class getZhiBiao implements getZhibiaoBlSer{
	
	/* (non-Javadoc)
	 * @see blService.getZhibiaoBlSer#getZhiBiao(java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<ZhiBiaoPO> getZhiBiao(Date start, Date end, String code) throws Exception {
		// TODO Auto-generated method stub
		long start1=start.getTime();
		long end1=end.getTime();
		int days=(int)((end1-start1)/(1 * 24 * 60 * 60 * 1000));
		int ones=days-days*2/7;
		Stack<ZhiBiaoPO> result=new Stack<>();
		List<ZhiBiaoPO> Result=new ArrayList<ZhiBiaoPO>() {
		};
		ClickOne one=new ClickOne();
		Date last=new Date(start.getTime() - (long)360 * 24 * 60 * 60 * 1000);
		List<StockPO> list=one.clickOne(last, end, code);
		int size=list.size();
		for(int a=0;a<ones;a++){
			double ATR=0.0;
			double MA=0.0;
			double MA2=0.0;
			double MA3=0.0;
			double MA4=0.0;
			double MA5=0.0;
			double MA6=0.0;
			double MA7=0.0;
			double DIF=0.0;
			double DEA=0.0;
			double K=0.0;
			double D=0.0;
			double J=0.0;
			//计算ATR
			double sum1=0.0;
			double result1=0;
			for(int i=0;i<7;i++){
				double t=0;
				for(int m=0;m<14;m++){
					StockPO po1=list.get(size-1-i-m-a);
					StockPO lastpo=list.get(size-2-i-m-a);
					double hi=Double.parseDouble(po1.getMaxPrice());
					double li=Double.parseDouble(po1.getMinPrice());
					double ci1=Double.parseDouble(lastpo.getEndPrice());
					double li1=Double.parseDouble(lastpo.getMinPrice());
					double t1=hi-li;
					double t2=Double.max(ci1-hi, hi-ci1);
					double t3=ci1-li1;
					t+=Double.max(t1,Double.max(t2, t3));
				}
				sum1+=t/14;
			}
			result1=sum1/6;
			String s1=new DecimalFormat("#.####").format(result1);
			ATR=Double.parseDouble(s1);
			
			double sum2=0;
			double result2=0;
			for(int i=0;i<5;i++){
				StockPO po2=list.get(size-1-i-a);
				sum2+=Double.parseDouble(po2.getEndPrice());
			}
			result2=sum2/5;
			String s2=new DecimalFormat("##.####").format(result2);
			MA=Double.parseDouble(s2);
			
			double sum3=0;
			double result3=0;
			for(int i=0;i<10;i++){
				StockPO po2=list.get(size-1-i-a);
				sum3+=Double.parseDouble(po2.getEndPrice());
			}
			result3=sum3/10;
			String s3=new DecimalFormat("##.####").format(result3);
			MA2=Double.parseDouble(s3);
			
			double sum4=0;
			double result4=0;
			for(int i=0;i<20;i++){
				StockPO po2=list.get(size-1-i-a);
				sum4+=Double.parseDouble(po2.getEndPrice());
			}
			result4=sum4/20;
			String s4=new DecimalFormat("##.####").format(result4);
			MA3=Double.parseDouble(s4);
			
			double sum5=0;
			double result5=0;
			for(int i=0;i<30;i++){
				StockPO po2=list.get(size-1-i-a);
				sum5+=Double.parseDouble(po2.getEndPrice());
			}
			result5=sum5/30;
			String s5=new DecimalFormat("##.####").format(result5);
			MA4=Double.parseDouble(s5);
			
			double sum6=0;
			double result6=0;
			for(int i=0;i<60;i++){
				StockPO po2=list.get(size-1-i-a);
				sum6+=Double.parseDouble(po2.getEndPrice());
			}
			result6=sum6/60;
			String s6=new DecimalFormat("##.####").format(result6);
			MA5=Double.parseDouble(s6);
			
			double sum7=0;
			double result7=0;
			for(int i=0;i<120;i++){
				StockPO po2=list.get(size-1-i-a);
				sum7+=Double.parseDouble(po2.getEndPrice());
			}
			result7=sum7/120;
			String s7=new DecimalFormat("##.####").format(result7);
			MA6=Double.parseDouble(s7);
			
			double sum8=0;
			double result8=0;
			for(int i=0;i<250;i++){
				StockPO po2=list.get(size-1-i-a);
				sum8+=Double.parseDouble(po2.getEndPrice());
			}
			result8=sum8/250;
			String s8=new DecimalFormat("##.####").format(result8);
			MA7=Double.parseDouble(s8);
			
			ZhiBiaoPO po=new ZhiBiaoPO(list.get(size-1-a).getDate(), ATR, MA,
					MA2, MA3, MA4, MA5, MA6, MA7, DIF, DEA,K,D,J);
			result.push(po);
		}
		
		double coEMA12=0;
		double coEMA26=0;
		double coDEA=0;
		double dif=0;
		double k=0;
		double d=0;
		double j=0;
		double sum3=0;
		double sum4=0;
		double sum5=0;
		
		//计算初始K,D,J
		double H9=0.0;
		double L9=0.0;
		for(int a=0;a<9;a++){
			StockPO po=list.get(size-ones-a);
			if(H9<Double.parseDouble(po.getEndPrice())){
				H9=Double.parseDouble(po.getEndPrice());
			}
			if(L9==0||L9>Double.parseDouble(po.getEndPrice())){
				L9=Double.parseDouble(po.getEndPrice());
			}
		}
		double ct=Double.parseDouble(list.get(size-ones).getEndPrice());
		double rsv=(ct-L9)/(H9-L9)*100;
		k=rsv;
		d=rsv;
		j=rsv;
		
		
		//sum3记录前11天收盘价之和，sum4记录前25天收盘价之和，sum5记录差值之和
		for(int b=0;b<8;b++){
			for(int a=0;a<11;a++){
			 StockPO po=list.get(size-2-ones-a-b-14);
			 sum3+=Double.parseDouble(po.getEndPrice());
			}
			StockPO PO=list.get(size-ones-b-1-14);
			coEMA12=(sum3+2*Double.parseDouble(PO.getEndPrice()))/13;
			for(int a=0;a<25;a++){
			 StockPO po=list.get(size-2-ones-a-b-14);
			 sum4+=Double.parseDouble(po.getEndPrice());
			}
			coEMA26=(sum4+2*Double.parseDouble(PO.getEndPrice()))/27;
			sum5+=(coEMA12-coEMA26);
		}
		sum3 = 0;
		sum4 = 0;
		for(int a=0;a<11;a++){
			 StockPO po=list.get(size-1-ones-a-14);
			 sum3+=Double.parseDouble(po.getEndPrice());
		}
		StockPO PO=list.get(size-ones-14);
		coEMA12=(sum3+2*Double.parseDouble(PO.getEndPrice()))/13;
		for(int a=0;a<25;a++){
			 StockPO po=list.get(size-2-ones-a-14);
			 sum4+=Double.parseDouble(po.getEndPrice());
		}
		coEMA26=(sum4+2*Double.parseDouble(PO.getEndPrice()))/27;
		dif=coEMA12-coEMA26;
		coDEA=(sum5+2*dif)/10;
		sum5 = 0;
		//通过14天缓冲来消除初始值的影响
		for(int n=-13;n<1;n++){
			StockPO po1=list.get(size-ones+n);
			double endPrice=Double.parseDouble(po1.getEndPrice());
			coEMA12=(coEMA12*11+2*endPrice)/13;
			coEMA26=(coEMA26*25+2*endPrice)/27;
			dif=coEMA12-coEMA26;
			coDEA=(coDEA*8+dif*2)/10;
		}
		ZhiBiaoPO po=result.pop();
		po.setDIF(dif);
		po.setDEA(coDEA);
		po.setK(k);
		po.setD(d);
		po.setJ(j);
		Result.add(po);
		for(int n=1;n<ones;n++){
			StockPO po1=list.get(size-ones+n);
			double endPrice=Double.parseDouble(po1.getEndPrice());
			ZhiBiaoPO po2=result.pop();
			
			double h9=0.0;
			double l9=0.0;
			double CT=Double.parseDouble(po1.getEndPrice());
			for(int c=0;c<9;c++){
				StockPO po3=list.get(size-ones+n-c);
				if(h9<Double.parseDouble(po3.getEndPrice())){
					h9=Double.parseDouble(po3.getEndPrice());
				}
				if(l9==0||l9>Double.parseDouble(po3.getEndPrice())){
					l9=Double.parseDouble(po3.getEndPrice());
				}
			}
			rsv=(CT-l9)/(h9-l9)*100;
			k=(rsv+2*k)/3;
			d=(k+2*d)/3;
			j=3*d-2*k;
			
			coEMA12=(coEMA12*11+2*endPrice)/13;
			coEMA26=(coEMA26*25+2*endPrice)/27;
			dif=coEMA12-coEMA26;
			String s3=new DecimalFormat("##.####").format(dif);
			coDEA=(coDEA*8+dif*2)/10;
			String s4=new DecimalFormat("##.####").format(coDEA);
			dif=Double.parseDouble(s3);
			String s5=new DecimalFormat("##.####").format(k);
			k=Double.parseDouble(s5);
			String s6=new DecimalFormat("##.####").format(d);
			d=Double.parseDouble(s6);
			String s7=new DecimalFormat("##.####").format(j);
			j=Double.parseDouble(s7);
			
			coDEA=Double.parseDouble(s4);
			po2.setDIF(dif);
			po2.setDEA(coDEA);
			po2.setK(k);
			po2.setD(d);
			po2.setJ(j);
			Result.add(po2);
		}
		return Result;
	}

}
