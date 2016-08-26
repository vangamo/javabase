package xyz.vangamo.base;

import java.util.Vector;

public class Base extends Vector<Object> {

    // Static attributes.

    /**
	 *
	 */
	private static final long serialVersionUID = 845271491656879076L;


	public static String[] FIELD_NAMES = {
/* 01 */    "id"
/* 02 */   ,"type"
/* 03 */   ,"app"
/* 04 */   ,"name"
            };

	public static String[] FIELD_TYPES = {
/* 01 */    "ref"
/* 02 */   ,"type"
/* 03 */   ,"text"
/* 04 */   ,"text"
            };




    // Instance attributes.

    protected String[]       FIELDS       = null;
    protected int            FIELD_STATES = 0;
    protected Vector<Object> DIRTY_VALUES = null;





    // Constructors.

    protected Base() {
    	this( Base.FIELD_NAMES );
    	}

    public Base( String[] fields ) {
    	this( fields.length );

    	this.FIELDS = fields;
    	}

    protected Base( int size ) {
    	super( size, 0 );

        this.DIRTY_VALUES = new Vector<Object>( size );
        this.DIRTY_VALUES.setSize(size);
        this.setSize(size);

        System.out.println( "Base() Size = "+this.size() );
        }





    // Instance methods.

    @Override
	public Object get( int fieldIdx ) {
    	if( null != this.DIRTY_VALUES.elementAt(fieldIdx) ) {
    		return this.DIRTY_VALUES.elementAt( fieldIdx );
    		}

        return this.elementAt( fieldIdx );
        }

    public Object get( String field ) {
        int fieldIdx = java.util.Arrays.asList( this.FIELDS ).indexOf( field );
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
        int fieldIdx = java.util.Arrays.asList( this.FIELDS ).indexOf( field );
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
    	for( String fieldName: this.FIELDS ) {
    		int fieldIdx = java.util.Arrays.asList( this.FIELDS ).indexOf( fieldName );
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
            String[] parentFieldNames = (String[])parent.getField("FIELD_NAMES").get(null);
            String[] parentFieldTypes = (String[])parent.getField("FIELD_TYPES").get(null);
            String[] childFieldNames  = (String[]) child.getField("FIELD_NAMES").get(null);
            String[] childFieldTypes  = (String[]) child.getField("FIELD_TYPES").get(null);

            int parentNumFields = parentFieldNames.length;
            int childNumFields  = childFieldNames.length;
            int finalNumFields  = parentNumFields + childNumFields;

            String[] allFieldNames = new String[ finalNumFields ];
            System.arraycopy( parentFieldNames, 0, allFieldNames, 0,               parentNumFields );
            System.arraycopy( childFieldNames,  0, allFieldNames, parentNumFields, childNumFields );

            child.getField("FIELD_NAMES").set( null, allFieldNames );

            String[] allFieldTypes = new String[ finalNumFields ];
            System.arraycopy( parentFieldTypes, 0, allFieldTypes, 0,               parentNumFields );
            System.arraycopy( childFieldTypes,  0, allFieldTypes, parentNumFields, childNumFields );

            child.getField("FIELD_TYPES").set( null, allFieldTypes );
            }
        catch( NoSuchFieldException   nsfEx ) { System.out.println(nsfEx); }
        catch( IllegalAccessException iaEx  ) { System.out.println(iaEx); }
        }

    }  //  class Base