package net.tisue.euler

// How many Sundays fell on the first of the month during the
// twentieth century (1 Jan 1901 to 31 Dec 2000)?

object Problem19 extends Problem(19, "171") {
  // The Java APIs for this are imperative so it's a bit awkward
  import java.util.Calendar
  def centuryBegin = {
    val c = Calendar.getInstance
    c.setTime(new java.text.SimpleDateFormat("MMM d yyyy")
              .parse("Jan 1 1901"))
    c
  }
  def nextMonth(cal: Calendar) = {
    val newCal = cal.clone.asInstanceOf[Calendar]
    newCal.add(java.util.Calendar.MONTH, 1)
    newCal
  }
  def solve =
    LazyList.iterate(centuryBegin)(nextMonth)
      .take(1200)
      .count(_.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
}
