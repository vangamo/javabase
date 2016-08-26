package xyz.vangamo.base;

import java.util.Vector;

public class Base extends Vector<Object> {

    // Static attributes.

    /**
	 *
	 */
	private static final long serialVersionUID = 845271491656879076L;


	public static String[] FIELDS = {
/* 01 */    "id",
/* 02 */    "type"
            };





    // Instance attributes.

    protected String[]       FIELDNAMES   = null;
//    protected Object[]       VALUES       = null;
    protected Vector<Object> DIRTY_VALUES = null;





    // Constructors.

    protected Base() {
    	this( Base.FIELDS );
    	}

    public Base( String[] fields ) {
    	this( fields.length );

    	this.FIELDNAMES = fields;
    	}

    protected Base( int size ) {
    	super( size, 0 );

//        this.VALUES       = new Object[ size ];
        this.DIRTY_VALUES = new Vector<Object>( size );
        this.DIRTY_VALUES.setSize(size);
        this.setSize(size);

        System.out.println( "Base() Size = "+this.size() );
/*
        for( int idx = size-1; idx >=0; --idx ) {
        	this.setElementAt( null, idx );
        	this.DIRTY_VALUES.setElementAt( null, idx );
        	}

System.out.println( "Base() Size = "+this.size() );
*/
        }





    // Instance methods.

    @Override
	public Object get( int fieldIdx ) {
/*
        if( null != this.DIRTY_VALUES[ fieldIdx] ) {
            return this.DIRTY_VALUES[ fieldIdx];
        	}
*/
    	if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) {
    		return this.DIRTY_VALUES.elementAt( fieldIdx );
    		}

//        return this.VALUES[ fieldIdx ];
        return this.elementAt( fieldIdx );
        }

    public Object get( String field ) {
        int fieldIdx = java.util.Arrays.asList( this.FIELDNAMES ).indexOf( field );
        if( fieldIdx < 0 ) { return null; }

        return this.get( fieldIdx );
        }

    public Long getLong( String field ) {
        return (Long)this.get( field );
        }

    public Long getLong( int fieldIdx ) {
        return (Long)this.get( fieldIdx );
        }

    public String getString( String field ) {
        return (String)this.get( field );
        }

    public String getString( int fieldIdx ) {
        return (String)this.get( fieldIdx );
        }



    @Override
	public Object set( int fieldIdx, Object value ) {
        Object oldValue = this.get( fieldIdx );
        if( (null != oldValue && null == value) || (null == oldValue && null != value) || (null != oldValue && null != value && !oldValue.equals(value)) ) {
System.out.println( "base.set() setting" );
	        this.DIRTY_VALUES.setElementAt( value, fieldIdx );

//	        this.VALUES[ fieldIdx ] = value;
        	}
        else {
System.out.println( "base.set() not setting" );
        	}

        return oldValue;
        }

    public Object set( String field, Object value ) {
        int fieldIdx = java.util.Arrays.asList( this.FIELDNAMES ).indexOf( field );
        if( fieldIdx < 0 ) { return null; }

        return this.set( fieldIdx, value );
        }

    public Long setLong( String field, Long value ) {
        return (Long)this.set( field, value );
        }

    public Long setLong( String field, long value ) {
        return (Long)this.set( field, new Long( value ) );
        }

    public Long setLong( int fieldIdx, Long value ) {
        return (Long)this.set( fieldIdx, value );
        }

    public Long setLong( int fieldIdx, long value ) {
        return (Long)this.set( fieldIdx, new Long( value ) );
        }

    public String setString( String field, String value ) {
        return (String)this.set( field, value );
        }

    public String setString( int fieldIdx, String value ) {
        return (String)this.set( fieldIdx, value );
        }



    public boolean isDirty() {
    	int idxFields = this.DIRTY_VALUES.size() - 1;
    	boolean isDirty = false;

    	while( !isDirty && idxFields>=0 ) {
    		isDirty = this.DIRTY_VALUES.elementAt( idxFields ) != null;
    		--idxFields;
    		}

    	return isDirty;
    	}

	@Override
	public String toString() {
    	String output = null;

    	output  = this.getClass().getSimpleName().toLowerCase();
    	output += " #" + String.format("%d", this.get("id"));
    	output += " (";

    	String separator = "";
    	for( String fieldName: this.FIELDNAMES ) {
    		int fieldIdx = java.util.Arrays.asList( this.FIELDNAMES ).indexOf( fieldName );
    		if( "id".equals(fieldName) ) {

    			}
    		else if( fieldName.endsWith("Time") ) {
    			output += separator + fieldName;
    			if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) { output += "*"; }
    			output += "=" + String.format( "%d", this.get(fieldName) );
    			separator = ", ";
    			}
    		else {
    			output += separator + fieldName;
    			if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) { output += "*"; }
    			output += "=" + String.format( "%s", this.get(fieldName) );
    			separator = ", ";
    			}

    		}
    	output += ")";
		return output;

    }





    // Static methods.

    protected static void COMPOSE_FIELDS( Class parent, Class child ) {
        try {
            String[] parentFields = (String[])parent.getField("FIELDS").get(null);
            String[] childFields  = (String[]) child.getField("FIELDS").get(null);

            int parentNumFields = parentFields.length;
            int childNumFields  = childFields.length;
            int finalNumFields  = parentNumFields + childNumFields;

            String[] allFields = new String[ finalNumFields ];

            System.arraycopy( parentFields, 0, allFields, 0,               parentNumFields );
            System.arraycopy( childFields,  0, allFields, parentNumFields, childNumFields );

            child.getField("FIELDS").set( null, allFields );
            }
        catch( NoSuchFieldException nsfEx ) { System.out.println(nsfEx); }
        catch( IllegalAccessException iaEx ) { System.out.println(iaEx); }
        }

    }  //  class Base