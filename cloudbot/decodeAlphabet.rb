require 'set'


# Joshua Calloway
# April 15, 2012
# Programming exercise for Interview @CloudBot
#
# Output
#
# ruby decodeAlphabet.rb
# alphabet sorted is [r, c, t, s, w, m, z, p, k, o, v, x, j, d, f, u, y, a, n, b, q, i, e, g, h, l]

class AlphabetGraph
  attr_reader :sortedAlphabet

  def initialize
    @edges = Set.new
    @charNodes = Set.new
    @adjGraph = {}
    @reverseAdjGraph = {}
    @visited = {}
    @sortedAlphabet = []
  end

  def add_char_node(node)
    @charNodes << node
  end

  def add_edge(edge)
    add_char_node edge.nodeA
    add_char_node edge.nodeB
    @edges << edge
    if ! (@adjGraph.has_key? edge.nodeA)
      @adjGraph[ edge.nodeA ] = Set.new
    end
    @adjGraph[ edge.nodeA ] << edge

    if ! (@reverseAdjGraph.has_key? edge.nodeB)
      @reverseAdjGraph[ edge.nodeB ] = Set.new
    end
    @reverseAdjGraph[ edge.nodeB ] << edge
  end

  def visit(node, list)
    if !@visited.has_key? node
      @visited[node] = true
      if @reverseAdjGraph.has_key? node
        @reverseAdjGraph[node].each do |edge|
          visit edge.nodeA, list
        end
      end
      list << node
    end
  end

  # wikipedia algorithm for topological sort
  def topological_sort
    l = []
    @visited = {}
    s = @charNodes - @adjGraph.keys
    s.each do |node|
      visit node, l
     end
    @sortedAlphabet = l
  end

  # print graph for debugging 
  def print
    puts "------ AlphabetGraph is ------"
    edges = ""
    @edges.each do |edge|
      edges << edge.to_s << ", "
    end
    puts "edges : #{edges}"
    vertices = ""
    @charNodes.each do |node|
      vertices << node.to_s << ", "
    end
    puts "vertices : #{vertices}"
  end
end

# mixin just to help out with printing of a Set
class Set
  def to_s
    to_a.join(', ')
  end
end

class CharNode
  attr_reader :char

  def initialize(char)
    @char = char
  end

  def to_s
    @char
  end

  def eql?(another)
    self.char == another.char
  end
 
  def hash
    @char.hash
  end

end

class Edge
  attr_reader :nodeA
  attr_reader :nodeB

  def initialize(nodeA, nodeB)
    @nodeA = nodeA
    @nodeB = nodeB
  end

  def eql?(another)
    self.nodeA == another.nodeA && self.nodeB == another.nodeB
  end

  def hash
    self.nodeA.hash + self.nodeB.hash
  end

  def to_s
    @nodeA.to_s + " -> " + @nodeB.to_s
  end
end


def get_largest_common_prefix(line1, line2)
  arr = [ "#{line1}", "#{line2}" ]
  prefix = arr.first.dup
  arr[1..-1].each do |e|
    prefix.slice!(e.size..-1) if e.size < prefix.size
    prefix.chop! while e.index(prefix) != 0
  end
  prefix
end


alphabet = AlphabetGraph.new
lines = File.open("alphabet.txt", "r").readlines
lineA = lines[0]
lines.shift
lines.each do |lineB|
  mostCommonPrefix = get_largest_common_prefix(lineA, lineB)
  regexPattern = "^#{mostCommonPrefix}([a-z]).*"
  lineB =~ /#{regexPattern}/
  charB = $1
  lineA =~ /#{regexPattern}/
  charA = $1
  if (!charA.nil? && !charB.nil?)
    edge = Edge.new CharNode.new(charA), CharNode.new(charB)
    alphabet.add_edge edge
  end
  lineA = lineB
end

alphabet.topological_sort
puts "alphabet sorted is #{alphabet.sortedAlphabet}"
