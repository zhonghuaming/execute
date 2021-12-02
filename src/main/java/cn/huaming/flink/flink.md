# 


### [Time](https://nightlies.apache.org/flink/flink-docs-release-1.14/docs/concepts/time/)
- ProcessingTime

- EventTime

### Event Time and Watermarks

The mechanism in Flink to measure progress in event time is **watermarks**.
Watermarks flow as part of the data stream and carry a timestamp t. A Watermark(t) declares that event time has reached time t in that stream,
meaning that there should be no more elements from the stream with a timestamp t' <= t (i.e. events with timestamps older or equal to the watermark).

Flink的测量事件时间进展的机制是水印。
水印作为数据流流动，水印声明事件时间已经到达了，意味着他们不再有t'<=t的元素。

### [Window](https://nightlies.apache.org/flink/flink-docs-release-1.14/docs/dev/datastream/operators/windows/#windows) 基本概念

Windows are at the heart of processing infinite streams.

Windows 是无界流的核心。将无界流进行拆分，得到有限的数据流。

### Window 的生命周期

### Window 分类
- key
- count
- time

### Window Assigners
- tumbling windows
- sliding windows
- session windows
- global windows

### 基于Window编程

### 基于WindowFunction

### Watermark

