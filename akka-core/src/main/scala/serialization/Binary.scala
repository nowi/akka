/**
 * Copyright (C) 2009-2010 Scalable Solutions AB <http://scalablesolutions.se>
 */

package se.scalablesolutions.akka.serialization

// --- PRIMITIVES ---
case class BinaryString(val value: String) extends Serializable.SBinary[BinaryString] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]): BinaryString = BinaryString(fromByteArray[String](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigInt(val value: BigInt) extends Serializable.SBinary[BinaryBigInt] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigInt(fromByteArray[BigInt](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimal(val value: BigDecimal) extends Serializable.SBinary[BinaryBigDecimal] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimal(fromByteArray[BigDecimal](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLong(val value: Long) extends Serializable.SBinary[BinaryLong] {
  import sbinary.DefaultProtocol._
  def this() = this(0L)
  def fromBytes(bytes: Array[Byte]) = BinaryLong(fromByteArray[Long](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryInt(val value: Int) extends Serializable.SBinary[BinaryInt] {
  import sbinary.DefaultProtocol._
  def this() = this(0)
  def fromBytes(bytes: Array[Byte]) = BinaryInt(fromByteArray[Int](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryDouble(val value: Double) extends Serializable.SBinary[BinaryDouble] {
  import sbinary.DefaultProtocol._
  def this() = this(0.0D)
  def fromBytes(bytes: Array[Byte]) = BinaryDouble(fromByteArray[Double](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryFloat(val value: Float) extends Serializable.SBinary[BinaryFloat] {
  import sbinary.DefaultProtocol._
  def this() = this(0.0F)
  def fromBytes(bytes: Array[Byte]) = BinaryFloat(fromByteArray[Float](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBoolean(val value: Boolean) extends Serializable.SBinary[BinaryBoolean] {
  import sbinary.DefaultProtocol._
  def this() = this(true)
  def fromBytes(bytes: Array[Byte]) = BinaryBoolean(fromByteArray[Boolean](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryByte(val value: Byte) extends Serializable.SBinary[BinaryByte] {
  import sbinary.DefaultProtocol._
  def this() = this(0x00)
  def fromBytes(bytes: Array[Byte]) = BinaryByte(fromByteArray[Byte](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryChar(val value: Char) extends Serializable.SBinary[BinaryChar] {
  import sbinary.DefaultProtocol._
  def this() = this(' ')
  def fromBytes(bytes: Array[Byte]) = BinaryChar(fromByteArray[Char](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}

// --- ARRAYS ---
case class BinaryStringArray(val value: Array[String]) extends Serializable.SBinary[BinaryStringArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringArray(fromByteArray[Array[String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigIntArray(val value: Array[BigInt]) extends Serializable.SBinary[BinaryBigIntArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigIntArray(fromByteArray[Array[BigInt]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimalArray(val value: Array[BigDecimal]) extends Serializable.SBinary[BinaryBigDecimalArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimalArray(fromByteArray[Array[BigDecimal]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryIntArray(val value: Array[Int]) extends Serializable.SBinary[BinaryIntArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryIntArray(fromByteArray[Array[Int]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLongArray(val value: Array[Long]) extends Serializable.SBinary[BinaryLongArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryLongArray(fromByteArray[Array[Long]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryDoubleArray(val value: Array[Double]) extends Serializable.SBinary[BinaryDoubleArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryDoubleArray(fromByteArray[Array[Double]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryFloatArray(val value: Array[Float]) extends Serializable.SBinary[BinaryFloatArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryFloatArray(fromByteArray[Array[Float]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBooleanArray(val value: Array[Boolean]) extends Serializable.SBinary[BinaryBooleanArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBooleanArray(fromByteArray[Array[Boolean]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryByteArray(val value: Array[Byte]) extends Serializable.SBinary[BinaryByteArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryByteArray(fromByteArray[Array[Byte]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryClassArray(val value: Array[Class[_]]) extends Serializable.SBinary[BinaryClassArray] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryClassArray(fromByteArray[Array[Class[_]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}

// --- LISTS ---
case class BinaryStringList(val value: List[String]) extends Serializable.SBinary[BinaryStringList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringList(fromByteArray[List[String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigIntList(val value: List[BigInt]) extends Serializable.SBinary[BinaryBigIntList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigIntList(fromByteArray[List[BigInt]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimalList(val value: List[BigDecimal]) extends Serializable.SBinary[BinaryBigDecimalList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimalList(fromByteArray[List[BigDecimal]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLongList(val value: List[Long]) extends Serializable.SBinary[BinaryLongList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryLongList(fromByteArray[List[Long]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryIntList(val value: List[Int]) extends Serializable.SBinary[BinaryIntList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryIntList(fromByteArray[List[Int]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryDoubleList(val value: List[Double]) extends Serializable.SBinary[BinaryDoubleList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryDoubleList(fromByteArray[List[Double]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryFloatList(val value: List[Float]) extends Serializable.SBinary[BinaryFloatList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryFloatList(fromByteArray[List[Float]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBooleanList(val value: List[Boolean]) extends Serializable.SBinary[BinaryBooleanList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBooleanList(fromByteArray[List[Boolean]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryClassList(val value: List[Class[_]]) extends Serializable.SBinary[BinaryClassList] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryClassList(fromByteArray[List[Class[_]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}

// --- TUPLES ---
case class BinaryStringStringTuple(val value: Tuple2[String, String]) extends Serializable.SBinary[BinaryStringStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringStringTuple(fromByteArray[Tuple2[String, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigIntBigIntTuple(val value: Tuple2[BigInt, BigInt]) extends Serializable.SBinary[BinaryBigIntBigIntTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigIntBigIntTuple(fromByteArray[Tuple2[BigInt, BigInt]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimalBigDecimalTuple(val value: Tuple2[BigDecimal, BigDecimal]) extends Serializable.SBinary[BinaryBigDecimalBigDecimalTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimalBigDecimalTuple(fromByteArray[Tuple2[BigDecimal, BigDecimal]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLongLongTuple(val value: Tuple2[Long, Long]) extends Serializable.SBinary[BinaryLongLongTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryLongLongTuple(fromByteArray[Tuple2[Long, Long]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryIntIntTuple(val value: Tuple2[Int, Int]) extends Serializable.SBinary[BinaryIntIntTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryIntIntTuple(fromByteArray[Tuple2[Int, Int]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryDoubleDoubleTuple(val value: Tuple2[Double, Double]) extends Serializable.SBinary[BinaryDoubleDoubleTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryDoubleDoubleTuple(fromByteArray[Tuple2[Double, Double]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryFloatFloatTuple(val value: Tuple2[Float, Float]) extends Serializable.SBinary[BinaryFloatFloatTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryFloatFloatTuple(fromByteArray[Tuple2[Float, Float]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBooleanBooleanTuple(val value: Tuple2[Boolean, Boolean]) extends Serializable.SBinary[BinaryBooleanBooleanTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBooleanBooleanTuple(fromByteArray[Tuple2[Boolean, Boolean]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryClassClassTuple(val value: Tuple2[Class[_], Class[_]]) extends Serializable.SBinary[BinaryClassClassTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryClassClassTuple(fromByteArray[Tuple2[Class[_], Class[_]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryByteArrayByteArrayTuple(val value: Tuple2[Array[Byte], Array[Byte]]) extends Serializable.SBinary[BinaryByteArrayByteArrayTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryByteArrayByteArrayTuple(fromByteArray[Tuple2[Array[Byte], Array[Byte]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigIntStringTuple(val value: Tuple2[BigInt, String]) extends Serializable.SBinary[BinaryBigIntStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigIntStringTuple(fromByteArray[Tuple2[BigInt, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimalStringTuple(val value: Tuple2[BigDecimal, String]) extends Serializable.SBinary[BinaryBigDecimalStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimalStringTuple(fromByteArray[Tuple2[BigDecimal, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLongStringTuple(val value: Tuple2[Long, String]) extends Serializable.SBinary[BinaryLongStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryLongStringTuple(fromByteArray[Tuple2[Long, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryIntStringTuple(val value: Tuple2[Int, String]) extends Serializable.SBinary[BinaryIntStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryIntStringTuple(fromByteArray[Tuple2[Int, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryDoubleStringTuple(val value: Tuple2[Double, String]) extends Serializable.SBinary[BinaryDoubleStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryDoubleStringTuple(fromByteArray[Tuple2[Double, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryFloatStringTuple(val value: Tuple2[Float, String]) extends Serializable.SBinary[BinaryFloatStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryFloatStringTuple(fromByteArray[Tuple2[Float, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBooleanStringTuple(val value: Tuple2[Boolean, String]) extends Serializable.SBinary[BinaryBooleanStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBooleanStringTuple(fromByteArray[Tuple2[Boolean, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryClassStringTuple(val value: Tuple2[Class[_], String]) extends Serializable.SBinary[BinaryClassStringTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryClassStringTuple(fromByteArray[Tuple2[Class[_], String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringByteArrayTuple(val value: Tuple2[String, Array[Byte]]) extends Serializable.SBinary[BinaryStringByteArrayTuple] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringByteArrayTuple(fromByteArray[Tuple2[String, Array[Byte]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}

// --- MAPS ---
case class BinaryStringStringMap(val value: Map[String, String]) extends Serializable.SBinary[BinaryStringStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringStringMap(fromByteArray[Map[String, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigIntStringMap(val value: Map[BigInt, String]) extends Serializable.SBinary[BinaryBigIntStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigIntStringMap(fromByteArray[Map[BigInt, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryBigDecimalStringMap(val value: Map[BigDecimal, String]) extends Serializable.SBinary[BinaryBigDecimalStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryBigDecimalStringMap(fromByteArray[Map[BigDecimal, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryLongStringMap(val value: Map[Long, String]) extends Serializable.SBinary[BinaryLongStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryLongStringMap(fromByteArray[Map[Long, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryIntStringMap(val value: Map[Int, String]) extends Serializable.SBinary[BinaryIntStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryIntStringMap(fromByteArray[Map[Int, String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryClassStringMap(val value: Map[Class[_], String]) extends Serializable.SBinary[BinaryClassStringMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryClassStringMap(fromByteArray[Map[Class[_], String]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringBigIntMap(val value: Map[String, BigInt]) extends Serializable.SBinary[BinaryStringBigIntMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringBigIntMap(fromByteArray[Map[String, BigInt]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringBigDecimalMap(val value: Map[String, BigDecimal]) extends Serializable.SBinary[BinaryStringBigDecimalMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringBigDecimalMap(fromByteArray[Map[String, BigDecimal]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringLongMap(val value: Map[String, Long]) extends Serializable.SBinary[BinaryStringLongMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringLongMap(fromByteArray[Map[String, Long]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringIntMap(val value: Map[String, Int]) extends Serializable.SBinary[BinaryStringIntMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringIntMap(fromByteArray[Map[String, Int]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringClassMap(val value: Map[String, Class[_]]) extends Serializable.SBinary[BinaryStringClassMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringClassMap(fromByteArray[Map[String, Class[_]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
case class BinaryStringByteArrayMap(val value: Map[String, Array[Byte]]) extends Serializable.SBinary[BinaryStringByteArrayMap] {
  import sbinary.DefaultProtocol._
  def this() = this(null)
  def fromBytes(bytes: Array[Byte]) = BinaryStringByteArrayMap(fromByteArray[Map[String, Array[Byte]]](bytes))
  def toBytes: Array[Byte] =          toByteArray(value)
}
