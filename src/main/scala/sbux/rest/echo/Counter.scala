package sbux.rest.echo

case class Counter(count: Int)

object Counter {
  def apply(): Counter = Counter(0)
  def increment(counter: Counter) = Counter(counter.count + 1)
}
