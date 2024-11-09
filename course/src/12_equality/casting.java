public boolean equals (Object thatObject) {
  // check if it is DUration type or not
  if (!(thatObject instanceof Duration)) return false;
  // casting
  Duration thatDuration = (Duration) thatObject;
  return this.getLength() == thatDuration.getLength();
}
