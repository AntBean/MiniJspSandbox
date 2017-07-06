package java.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.ObjectOutputStream.PutField;
import java.io.ObjectStreamField;
import java.io.Serializable;

public final class StringBuffer
  extends AbstractStringBuilder
  implements Serializable, CharSequence
{
  static final long serialVersionUID = 3388685877147921107L;
  
  boolean taint = false;
  
  public StringBuffer()
  {
    super(16);
  }
  
  public StringBuffer(int paramInt)
  {
    super(paramInt);
  }
  
  public StringBuffer(String paramString)
  {
    super(paramString.length() + 16);
    append(paramString);
  }
  
  public StringBuffer(CharSequence paramCharSequence)
  {
    this(paramCharSequence.length() + 16);
    append(paramCharSequence);
  }
  
  public boolean getTaint(){
	  return taint;
  }
  
  public synchronized int length()
  {
    return this.count;
  }
  
  public synchronized int capacity()
  {
    return this.value.length;
  }
  
  public synchronized void ensureCapacity(int paramInt)
  {
    if (paramInt > this.value.length) {
      expandCapacity(paramInt);
    }
  }
  
  public synchronized void trimToSize()
  {
    super.trimToSize();
  }
  
  public synchronized void setLength(int paramInt)
  {
    super.setLength(paramInt);
  }
  
  public synchronized char charAt(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.count)) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    return this.value[paramInt];
  }
  
  public synchronized int codePointAt(int paramInt)
  {
    return super.codePointAt(paramInt);
  }
  
  public synchronized int codePointBefore(int paramInt)
  {
    return super.codePointBefore(paramInt);
  }
  
  public synchronized int codePointCount(int paramInt1, int paramInt2)
  {
    return super.codePointCount(paramInt1, paramInt2);
  }
  
  public synchronized int offsetByCodePoints(int paramInt1, int paramInt2)
  {
    return super.offsetByCodePoints(paramInt1, paramInt2);
  }
  
  public synchronized void getChars(int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3)
  {
    super.getChars(paramInt1, paramInt2, paramArrayOfChar, paramInt3);
  }
  
  public synchronized void setCharAt(int paramInt, char paramChar)
  {
    if ((paramInt < 0) || (paramInt >= this.count)) {
      throw new StringIndexOutOfBoundsException(paramInt);
    }
    this.value[paramInt] = paramChar;
  }
  
  public synchronized StringBuffer append(Object paramObject)
  {
    super.append(String.valueOf(paramObject));
    return this;
  }
  
  public synchronized StringBuffer append(String paramString)
  {
    super.append(paramString);
    
    if(paramString!= null && paramString.getTaint()){
    	taint = true;
    }
    
    return this;
  }
  
  public synchronized StringBuffer append(StringBuffer paramStringBuffer)
  {
    super.append(paramStringBuffer);
    
    if(paramStringBuffer!= null && paramStringBuffer.getTaint()){
    	taint = true;
    }
    
    return this;
  }
  
  public StringBuffer append(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      paramCharSequence = "null";
    }
    if ((paramCharSequence instanceof String)) {
      return append((String)paramCharSequence);
    }
    if ((paramCharSequence instanceof StringBuffer)) {
      return append((StringBuffer)paramCharSequence);
    }
    return append(paramCharSequence, 0, paramCharSequence.length());
  }
  
  public synchronized StringBuffer append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    super.append(paramCharSequence, paramInt1, paramInt2);
    return this;
  }
  
  public synchronized StringBuffer append(char[] paramArrayOfChar)
  {
    super.append(paramArrayOfChar);
    return this;
  }
  
  public synchronized StringBuffer append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    super.append(paramArrayOfChar, paramInt1, paramInt2);
    return this;
  }
  
  public synchronized StringBuffer append(boolean paramBoolean)
  {
    super.append(paramBoolean);
    return this;
  }
  
  public synchronized StringBuffer append(char paramChar)
  {
    super.append(paramChar);
    return this;
  }
  
  public synchronized StringBuffer append(int paramInt)
  {
    super.append(paramInt);
    return this;
  }
  
  public synchronized StringBuffer appendCodePoint(int paramInt)
  {
    super.appendCodePoint(paramInt);
    return this;
  }
  
  public synchronized StringBuffer append(long paramLong)
  {
    super.append(paramLong);
    return this;
  }
  
  public synchronized StringBuffer append(float paramFloat)
  {
    super.append(paramFloat);
    return this;
  }
  
  public synchronized StringBuffer append(double paramDouble)
  {
    super.append(paramDouble);
    return this;
  }
  
  public synchronized StringBuffer delete(int paramInt1, int paramInt2)
  {
    super.delete(paramInt1, paramInt2);
    return this;
  }
  
  public synchronized StringBuffer deleteCharAt(int paramInt)
  {
    super.deleteCharAt(paramInt);
    return this;
  }
  
  public synchronized StringBuffer replace(int paramInt1, int paramInt2, String paramString)
  {
    super.replace(paramInt1, paramInt2, paramString);
    return this;
  }
  
  public synchronized String substring(int paramInt)
  {
    return substring(paramInt, this.count);
  }
  
  public synchronized CharSequence subSequence(int paramInt1, int paramInt2)
  {
    return super.substring(paramInt1, paramInt2);
  }
  
  public synchronized String substring(int paramInt1, int paramInt2)
  {
    return super.substring(paramInt1, paramInt2);
  }
  
  public synchronized StringBuffer insert(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    super.insert(paramInt1, paramArrayOfChar, paramInt2, paramInt3);
    return this;
  }
  
  public synchronized StringBuffer insert(int paramInt, Object paramObject)
  {
    super.insert(paramInt, String.valueOf(paramObject));
    return this;
  }
  
  public synchronized StringBuffer insert(int paramInt, String paramString)
  {
    super.insert(paramInt, paramString);
    return this;
  }
  
  public synchronized StringBuffer insert(int paramInt, char[] paramArrayOfChar)
  {
    super.insert(paramInt, paramArrayOfChar);
    return this;
  }
  
  public StringBuffer insert(int paramInt, CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      paramCharSequence = "null";
    }
    if ((paramCharSequence instanceof String)) {
      return insert(paramInt, (String)paramCharSequence);
    }
    return insert(paramInt, paramCharSequence, 0, paramCharSequence.length());
  }
  
  public synchronized StringBuffer insert(int paramInt1, CharSequence paramCharSequence, int paramInt2, int paramInt3)
  {
    super.insert(paramInt1, paramCharSequence, paramInt2, paramInt3);
    return this;
  }
  
  public StringBuffer insert(int paramInt, boolean paramBoolean)
  {
    return insert(paramInt, String.valueOf(paramBoolean));
  }
  
  public synchronized StringBuffer insert(int paramInt, char paramChar)
  {
    super.insert(paramInt, paramChar);
    return this;
  }
  
  public StringBuffer insert(int paramInt1, int paramInt2)
  {
    return insert(paramInt1, String.valueOf(paramInt2));
  }
  
  public StringBuffer insert(int paramInt, long paramLong)
  {
    return insert(paramInt, String.valueOf(paramLong));
  }
  
  public StringBuffer insert(int paramInt, float paramFloat)
  {
    return insert(paramInt, String.valueOf(paramFloat));
  }
  
  public StringBuffer insert(int paramInt, double paramDouble)
  {
    return insert(paramInt, String.valueOf(paramDouble));
  }
  
  public int indexOf(String paramString)
  {
    return indexOf(paramString, 0);
  }
  
  public synchronized int indexOf(String paramString, int paramInt)
  {
    return String.indexOf(this.value, 0, this.count, paramString.toCharArray(), 0, paramString.length(), paramInt);
  }
  
  public int lastIndexOf(String paramString)
  {
    return lastIndexOf(paramString, this.count);
  }
  
  public synchronized int lastIndexOf(String paramString, int paramInt)
  {
    return String.lastIndexOf(this.value, 0, this.count, paramString.toCharArray(), 0, paramString.length(), paramInt);
  }
  
  public synchronized StringBuffer reverse()
  {
    super.reverse();
    return this;
  }
  
  public synchronized String toString()
  {
    String result = new String(this.value, 0, this.count);
    result.setTaint(taint);
    return result;
  }
  
  private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField("value", char[].class), new ObjectStreamField("count", Integer.TYPE), new ObjectStreamField("shared", Boolean.TYPE) };
  
  private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    ObjectOutputStream.PutField localPutField = paramObjectOutputStream.putFields();
    localPutField.put("value", this.value);
    localPutField.put("count", this.count);
    localPutField.put("shared", false);
    paramObjectOutputStream.writeFields();
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    ObjectInputStream.GetField localGetField = paramObjectInputStream.readFields();
    this.value = ((char[])localGetField.get("value", null));
    this.count = localGetField.get("count", 0);
  }
}
