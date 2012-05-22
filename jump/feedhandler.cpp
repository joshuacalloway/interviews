#include <string>
#include <sstream>
#include <fstream>
#include <iostream>
#include <queue>
#include <vector>
#include <map>
#include <cstdlib>

using namespace std;


enum PossibleMessageType { ADD, MODIFY, CANCEL, UNKNOWN };

class MessageType {

private:
  PossibleMessageType type;

public:
  bool operator==(const MessageType& other) const;
  bool operator!=(const MessageType& other) const;
  MessageType(const MessageType& other) : type(other.type) {}
  PossibleMessageType getType() const { return type; }
  MessageType(const PossibleMessageType &t) : type(t) {}
  MessageType() {}
  MessageType(std::string s) {
    char first = s[0];

    switch (first) {
    case 'A':
      type = ADD;
      break;
    case 'C':
      type = CANCEL;
      break;
    case 'M':
      type = MODIFY;
      break;
    }
  }
};

bool MessageType::operator==(const MessageType& other) const {
  return other.type == type;
}

bool MessageType::operator!=(const MessageType& other) const {
  return other.type != type;
}

enum OrderType { BUY, SELL };

class Order
{
private:
  MessageType messageType;
  OrderType type;
  int orderId;
  int quantity;
  double price;

public:
  Order() {}
  Order(const Order& o) : type(o.type), orderId(o.orderId), quantity(o.quantity), price(o.price) {
    
  }
  Order(MessageType t, int id) : messageType(t), orderId(id) {
  }
  
  void setQuantity(int qty) { quantity = qty; };
  void setPrice(double price) { this->price = price; } 
  int getOrderId() const { return orderId; }
  OrderType getType() const { return type; }
  double getPrice() const { return price; }
  int getQuantity() const { return quantity; }
  void setType(OrderType t) { type = t; }
  MessageType getMessageType() const { return messageType; }
  friend ostream& operator<<(ostream& out, const Order& o);
};

class OrderBook 
{
private:
  map<int, Order> orders;
  map<OrderType, map<double, vector<Order> > > book;

  OrderType getOpposite(const OrderType& o) const {
    if (o == SELL) return BUY;
    return SELL;
  }


  void addBook(Order& o) {
    cout << "addBook " << o << endl;
    book[o.getType()][o.getPrice()].push_back(o);
  }

  void matchBook(Order& o) {
    OrderType opposite = getOpposite(o.getType());
    map<double, vector<Order> > oppositeOrders = book[opposite];
    if (oppositeOrders.find(o.getPrice()) != oppositeOrders.end()) {
      vector<Order> orders = oppositeOrders[o.getPrice()];
      while (o.getQuantity() > 0 && orders.size() > 0) {
	Order first = orders.front();
	if (o.getQuantity() < first.getQuantity()) {
	  first.setQuantity(first.getQuantity() - o.getQuantity());
	} else {
	  orders.erase(orders.begin(), orders.begin()+1);
	}
	o.setQuantity(o.getQuantity() - first.getQuantity());
      }
    }
  }

public:
  friend ostream& operator<<(ostream& out, const OrderBook& b);

  void addOrder(const Order& o) {
    
    if (o.getMessageType() == CANCEL) {
      orders.erase(o.getOrderId());
    } else {
      Order nonconstOrder = o;
      matchBook(nonconstOrder);
      if (nonconstOrder.getQuantity() > 0) {
	addBook(nonconstOrder);
	orders[nonconstOrder.getOrderId()] = nonconstOrder;
      }
    }
  }
};

class FeedHandler
{
private:
  OrderBook book;
  
  OrderType toOrderType(string s);

public:
  FeedHandler();
  void processMessage(const std::string &line);
  void printCurrentOrderBook(std::ostream &os) const;
  void printErrorSummary(std::ostream &os) const;
};

FeedHandler::FeedHandler() {
}
void FeedHandler::printCurrentOrderBook(std::ostream &out) const {
  out << "--------" << endl;
  out << book;
  out << "--------" << endl;
}
void FeedHandler::printErrorSummary(std::ostream &os) const {

}

OrderType FeedHandler::toOrderType(string s) {
  char first = s[0];
  OrderType type;
  switch (first) {
  case 'B':
    type= BUY;
    break;
  case 'S':
    type= SELL;
    break;
  }
  return type;
}

void outputOrders(ostream& out, 
		  map<double, vector<Order> >::reverse_iterator beg, 
		  map<double, vector<Order> >::reverse_iterator end) {
  while ( beg != end) {
    out << (*beg).first;
    out << " ";
    vector<Order> list = (*beg).second;
    for (vector<Order>::iterator j = list.begin(); j != list.end(); j++) {
      Order next = (*j);
      if (next.getType() == SELL) { out << "s"; }
      else { out << "b"; }
      out << " " << next.getQuantity();
      out << "     ";
    }
    out << endl;
    beg++;
  }
}

ostream& operator<<(ostream& out, const OrderBook& b) {
  map<double, vector<Order> > sellOrders = (*b.book.find(SELL)).second;
  out << "sellOrders size : " << sellOrders.size();
  outputOrders(out, sellOrders.rbegin(), sellOrders.rend());
  map<double, vector<Order> > buyOrders = (*b.book.find(BUY)).second;
  outputOrders(out, buyOrders.rbegin(), buyOrders.rend());

  return out;

}

ostream& operator<<(ostream& out, const MessageType t) {
  switch (t.getType()) {
  case CANCEL:
    out << "CANCEL";
    break;
  case MODIFY:
    out << "MODIFY";
    break;
  case ADD:
    out << "ADD";
    break;

  };
  return out;
}
ostream& operator<<(ostream& out, const OrderType& o) {
  if (o == SELL) { out << "S"; }
  else { out << "B"; }
  return out;
}
ostream& operator<<(ostream& out, const Order& o) {
  out << "[" << o.orderId << ", " << o.messageType;
  if (o.messageType != CANCEL) {
    out << ", " << o.type << ", " << o.quantity << " @ " << o.price;
  }
  out << "]";
  return out;
}

void FeedHandler::processMessage(const std::string &line) {
  istringstream ss (line);
  string tmp;
  
  getline(ss, tmp, ',');
  MessageType messageType(tmp);
  int orderId;
  getline(ss, tmp, ',');
  orderId = atoi(tmp.c_str());
  getline(ss, tmp, ',');

  Order order(messageType, orderId);
  if (messageType != CANCEL) {
    OrderType orderType = toOrderType(tmp);
    getline(ss, tmp, ',');
    int quantity = atoi(tmp.c_str());
    getline(ss, tmp, ',');
    double price = atof(tmp.c_str());
    order.setType(orderType);
    order.setPrice(price);
    order.setQuantity(quantity);
  }
  book.addOrder(order);
  //  cout << "Order : " << order << endl;
}

int main(int argc, char **argv)
{
  FeedHandler feed;
  std::string line;
  const std::string filename(argv[1]);
  std::ifstream infile(filename.c_str());
  int counter = 0;
  while (std::getline(infile, line)) {
    feed.processMessage(line);
    if (++counter % 10 == 0) {
      feed.printCurrentOrderBook(std::cout);
    }
  }
  feed.printCurrentOrderBook(std::cout);
  feed.printErrorSummary(std::cerr);
  return 0;
}
