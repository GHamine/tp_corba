package TravelAgencyModule;


/**
* TravelAgencyModule/TravelAgencyHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from C:/Users/Amine/Desktop/M2/tp_corba/src/main/idl/TravelAgency.idl
* Saturday, December 28, 2024 3:51:26 PM WAT
*/

abstract public class TravelAgencyHelper
{
  private static String  _id = "IDL:TravelAgencyModule/TravelAgency:1.0";

  public static void insert (org.omg.CORBA.Any a, TravelAgencyModule.TravelAgency that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static TravelAgencyModule.TravelAgency extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (TravelAgencyModule.TravelAgencyHelper.id (), "TravelAgency");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static TravelAgencyModule.TravelAgency read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_TravelAgencyStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, TravelAgencyModule.TravelAgency value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static TravelAgencyModule.TravelAgency narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TravelAgencyModule.TravelAgency)
      return (TravelAgencyModule.TravelAgency)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TravelAgencyModule._TravelAgencyStub stub = new TravelAgencyModule._TravelAgencyStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static TravelAgencyModule.TravelAgency unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof TravelAgencyModule.TravelAgency)
      return (TravelAgencyModule.TravelAgency)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      TravelAgencyModule._TravelAgencyStub stub = new TravelAgencyModule._TravelAgencyStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
