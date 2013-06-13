/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/lab/projzesp2012/Execute/src/pl/diya/execute2/IRemoteService.aidl
 */
package pl.diya.execute2;
// service interface

public interface IRemoteService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements pl.diya.execute2.IRemoteService
{
private static final java.lang.String DESCRIPTOR = "pl.diya.execute2.IRemoteService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an pl.diya.execute2.IRemoteService interface,
 * generating a proxy if needed.
 */
public static pl.diya.execute2.IRemoteService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof pl.diya.execute2.IRemoteService))) {
return ((pl.diya.execute2.IRemoteService)iin);
}
return new pl.diya.execute2.IRemoteService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_sayHello:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.sayHello(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements pl.diya.execute2.IRemoteService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
//sample method

@Override public java.lang.String sayHello(java.lang.String message) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(message);
mRemote.transact(Stub.TRANSACTION_sayHello, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_sayHello = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
//sample method

public java.lang.String sayHello(java.lang.String message) throws android.os.RemoteException;
}
