package java.lang;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class StringBuilder
  extends AbstractStringBuilder
  implements Serializable, CharSequence
{
  static final long serialVersionUID = 4383685877147921099L;
  
  boolean taint = false;
  
  public StringBuilder()
  {
    super(16);
  }
  
  public StringBuilder(int paramInt)
  {
    super(paramInt);
  }
  
  public StringBuilder(String paramString)
  {
    super(paramString.length() + 16);
    append(paramString);
    
    if(paramString!=null && paramString.getTaint()){
    	taint = true;
    }
  }
  
  public StringBuilder(CharSequence paramCharSequence)
  {
    this(paramCharSequence.length() + 16);
    append(paramCharSequence);
  }
  
  public boolean getTaint(){
	  return taint;
  }
  
  
  public StringBuilder append(Object paramObject)
  {
    return append(String.valueOf(paramObject));
  }
  
  public StringBuilder append(String paramString)
  {
    super.append(paramString);
    
    if(paramString!= null && paramString.getTaint()){
    	taint = true;
    }
    
    return this;
  }
  
  private StringBuilder append(StringBuilder paramStringBuilder)
  {
    if (paramStringBuilder == null) {
      return append("null");
    }
    int i = paramStringBuilder.length();
    int j = this.count + i;
    if (j > this.value.length) {
      expandCapacity(j);
    }
    paramStringBuilder.getChars(0, i, this.value, this.count);
    this.count = j;
    
    if(paramStringBuilder!= null && paramStringBuilder.getTaint()){
    	taint = true;
    }
    
    return this;
  }
  
  public StringBuilder append(StringBuffer paramStringBuffer)
  {
    super.append(paramStringBuffer);
    
    if(paramStringBuffer!= null && paramStringBuffer.getTaint()){
    	taint = true;
    }
    
    return this;
  }
  
  public StringBuilder append(CharSequence paramCharSequence)
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
    if ((paramCharSequence instanceof StringBuilder)) {
      return append((StringBuilder)paramCharSequence);
    }
    return append(paramCharSequence, 0, paramCharSequence.length());
  }
  
  public StringBuilder append(CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    super.append(paramCharSequence, paramInt1, paramInt2);
    return this;
  }
  
  public StringBuilder append(char[] paramArrayOfChar)
  {
    super.append(paramArrayOfChar);
    return this;
  }
  
  public StringBuilder append(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    super.append(paramArrayOfChar, paramInt1, paramInt2);
    return this;
  }
  
  public StringBuilder append(boolean paramBoolean)
  {
    super.append(paramBoolean);
    return this;
  }
  
  public StringBuilder append(char paramChar)
  {
    super.append(paramChar);
    return this;
  }
  
  public StringBuilder append(int paramInt)
  {
    super.append(paramInt);
    return this;
  }
  
  public StringBuilder append(long paramLong)
  {
    super.append(paramLong);
    return this;
  }
  
  public StringBuilder append(float paramFloat)
  {
    super.append(paramFloat);
    return this;
  }
  
  public StringBuilder append(double paramDouble)
  {
    super.append(paramDouble);
    return this;
  }
  
  public StringBuilder appendCodePoint(int paramInt)
  {
    super.appendCodePoint(paramInt);
    return this;
  }
  
  public StringBuilder delete(int paramInt1, int paramInt2)
  {
    super.delete(paramInt1, paramInt2);
    return this;
  }
  
  public StringBuilder deleteCharAt(int paramInt)
  {
    super.deleteCharAt(paramInt);
    return this;
  }
  
  public StringBuilder replace(int paramInt1, int paramInt2, String paramString)
  {
    super.replace(paramInt1, paramInt2, paramString);
    return this;
  }
  
  public StringBuilder insert(int paramInt1, char[] paramArrayOfChar, int paramInt2, int paramInt3)
  {
    super.insert(paramInt1, paramArrayOfChar, paramInt2, paramInt3);
    return this;
  }
  
  public StringBuilder insert(int paramInt, Object paramObject)
  {
    return insert(paramInt, String.valueOf(paramObject));
  }
  
  public StringBuilder insert(int paramInt, String paramString)
  {
    super.insert(paramInt, paramString);
    return this;
  }
  
  public StringBuilder insert(int paramInt, char[] paramArrayOfChar)
  {
    super.insert(paramInt, paramArrayOfChar);
    return this;
  }
  
  public StringBuilder insert(int paramInt, CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      paramCharSequence = "null";
    }
    if ((paramCharSequence instanceof String)) {
      return insert(paramInt, (String)paramCharSequence);
    }
    return insert(paramInt, paramCharSequence, 0, paramCharSequence.length());
  }
  
  public StringBuilder insert(int paramInt1, CharSequence paramCharSequence, int paramInt2, int paramInt3)
  {
    super.insert(paramInt1, paramCharSequence, paramInt2, paramInt3);
    return this;
  }
  
  public StringBuilder insert(int paramInt, boolean paramBoolean)
  {
    super.insert(paramInt, paramBoolean);
    return this;
  }
  
  public StringBuilder insert(int paramInt, char paramChar)
  {
    super.insert(paramInt, paramChar);
    return this;
  }
  
  public StringBuilder insert(int paramInt1, int paramInt2)
  {
    return insert(paramInt1, String.valueOf(paramInt2));
  }
  
  public StringBuilder insert(int paramInt, long paramLong)
  {
    return insert(paramInt, String.valueOf(paramLong));
  }
  
  public StringBuilder insert(int paramInt, float paramFloat)
  {
    return insert(paramInt, String.valueOf(paramFloat));
  }
  
  public StringBuilder insert(int paramInt, double paramDouble)
  {
    return insert(paramInt, String.valueOf(paramDouble));
  }
  
  public int indexOf(String paramString)
  {
    return indexOf(paramString, 0);
  }
  
  public int indexOf(String paramString, int paramInt)
  {
    return String.indexOf(this.value, 0, this.count, paramString.toCharArray(), 0, paramString.length(), paramInt);
  }
  
  public int lastIndexOf(String paramString)
  {
    return lastIndexOf(paramString, this.count);
  }
  
  public int lastIndexOf(String paramString, int paramInt)
  {
    return String.lastIndexOf(this.value, 0, this.count, paramString.toCharArray(), 0, paramString.length(), paramInt);
  }
  
  public StringBuilder reverse()
  {
    super.reverse();
    return this;
  }
  
  public String toString()
  {
    String result = new String(this.value, 0, this.count);
    result.setTaint(taint);
    return result;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    paramObjectOutputStream.writeInt(this.count);
    paramObjectOutputStream.writeObject(this.value);
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    this.count = paramObjectInputStream.readInt();
    this.value = ((char[])paramObjectInputStream.readObject());
  }
}
