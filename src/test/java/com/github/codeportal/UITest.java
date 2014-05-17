import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ErrorCollector;
import org.junit.Assume;

public class UITest{

	//correct attribute
	final Index.Attrs [] cwt={Index.Attrs.CRIME,Index.Attrs.DISTANCE,Index.Attrs.PRICE,
		Index.Attrs.SCHOOL};

	
	//incorrect attributes
	final Index.Attrs [] b2wt={null,null,null,null};

	//length error
	final Index.Attrs [] b3wt={Index.Attrs.CRIME,Index.Attrs.DISTANCE,Index.Attrs.PRICE};

	final Index.Attrs [] b4wt={Index.Attrs.CRIME,Index.Attrs.DISTANCE,Index.Attrs.PRICE,
	Index.Attrs.SCHOOL,Index.Attrs.CRIME};

	final Index.Attrs [] b5wt={Index.Attrs.CRIME,Index.Attrs.CRIME,Index.Attrs.CRIME,
		Index.Attrs.CRIME};
	
	Index j;
	OMUI t;
	Query Q;

	/*STATES INDEX*/
	int min_index,max_index;
	
	int s;
	float pos;

	@Before
	public void initialize(){
		j=new Index();
		min_index=0;
		max_index=j.states.length-1;
		Random r=new Random();
		t=new OMUI();
		s=r.nextInt(max_index);
		pos=r.nextFloat()*(Float.MAX_VALUE-0.0f)+0.0f;
	}
	@Test
	public void nullObject(){
		Q=t.validateData(0,pos,j.states[s],cwt);
		if(Q==null) fail("REMAINING TESTS UNABLE TO EVALUATE NULL OBJECT");
		Assume.assumeNotNull(Q);
	}
	@Test 
	public void invalidInterval(){
		Q=t.validateData(pos,pos,j.states[s],cwt);
		assertTrue(Q.L<Q.U);
	}
	@Test
	public void null_attributes(){
		Q=t.validateData(0,pos,j.states[s],b2wt);
		for(int k=0;k<Q.ATTRS_SIZE;k++)
			assertFalse(Q.wt[k]==null);
	}
	@Test
	public void missing_attributes(){
		Q=t.validateData(0,pos,j.states[s],b3wt);
		assertFalse(Q.wt.length<Q.ATTRS_SIZE);
	}
	@Test
	public void  excess_attributes(){
		Q=t.validateData(0,pos,j.states[s],b4wt);
		assertFalse(Q.wt.length>Q.ATTRS_SIZE);	
	}
	@Test
	public void duplicate_attributes(){
		Q=t.validateData(0,pos,j.states[s],b5wt);
		for(int a=0;a<Q.ATTRS_SIZE;a++)
			for(int b=0;b<Q.ATTRS_SIZE;b++)
				if((a!=b) && (Q.wt[a]==Q.wt[b]))
					fail();
	}
}
