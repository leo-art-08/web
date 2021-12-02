#include <iostream>
using namespace std;
struct Point {
using point_t = tuple<double, double, double>;
//Осуществляем доступ к координатам по имени координат(x,y,z) 
double x;
double y;
double z;
Point() : x(0), y(0), z(0) { }
Point(double x, double y, double z) : x(x), y(y), z(z) { }
double length(const Point& p)const {
return sqrt(pow(p.x - x, 2) + pow(p.y - y, 2) + pow(p.z - z, 2));
}

friend istream& operator>>(istream& in, Point& p) {
cout << "x: ";
in >> p.x;
cout << "y: ";
in >> p.y;
cout << "z: ";
in >> p.z;
return in;
}
friend ostream& operator<<(ostream& out, const Point& p) {
return out << " { " << p.x << ", " << p.y << ", " << p.z << " } ";
}
} ;
class Vector3d {
public:
Vector3d() : p_(Point { } ) { }
Vector3d(const Point p) : p_(p) { }
void set(const Point p) {
p_ = p;
}
double length()const {
Point p;
return p_.length(p);
}
//Доступ к координатам по номеру координаты 
private:
Point p_;
friend Vector3d operator+(const Vector3d& a, const Vector3d& b) {
Point p;
//Сложение векторов
    p.x = a.p_.x + b.p_.x;
    p.y = a.p_.y + b.p_.y;
    p.z = a.p_.z + b.p_.z;
return { p } ;
}
friend Vector3d operator-(const Vector3d& a, const Vector3d& b) {
Point p;
// вычитание векторов
    p.x = a.p_.x - b.p_.x;
    p.y = a.p_.y - b.p_.y;
    p.z = a.p_.z - b.p_.z;
return { p } ;
}

//Умножение на число СЛЕВА
friend double operator*(const Vector3d& a, const Vector3d& b) {
return a.p_.x * b.p_.x + a.p_.y * b.p_.y + a.p_.z * b.p_.z;
}

//Виды умножения
friend Vector3d operator*(const Vector3d& v, const double n) {
Point p;
    p.x = v.p_.x * n;
    p.y = v.p_.y * n;
    p.z = v.p_.z * n;
Vector3d t(p);
return t;
}
friend Vector3d operator*(const double n, const Vector3d& v) {
Point p;
    p.x = v.p_.x * n;
    p.y = v.p_.y * n;
    p.z = v.p_.z * n;
Vector3d t(p);
return t;
}
friend bool operator<(const Vector3d& a, const Vector3d& b) {
return a.length() < b.length();
}
friend bool operator==(const Vector3d& a, const Vector3d& b) {
return a.length() == b.length();
}
friend bool operator!=(const Vector3d& a, const Vector3d& b) {
return !(a == b);
}
friend bool operator<=(const Vector3d& a, const Vector3d& b) {
return a < b || a == b;
}
friend bool operator>(const Vector3d& a, const Vector3d& b) {
return !(a <= b);
}
friend bool operator>=(const Vector3d& a, const Vector3d& b) {
return !(a < b);
}
friend istream& operator>>(istream& in, Vector3d& v) {
return in >> v.p_;
}
friend ostream& operator<<(ostream& out, const Vector3d& v) {
return out << v.p_;
}
} ;


//Вывод/ввод векторов
int main() {
cout << "Vector A:\n";
Vector3d a;
cin >> a;
cout << "Vector B:\n";
Vector3d b;
cin >> b;
auto ab = a + b;
cout << a << " + " << b << " = " << ab << '\n';
ab = a - b;
cout << a << " - " << b << " = " << ab << '\n';
auto mult = a * b;
cout << a << " * " << b << " = " << mult << '\n';
auto an = a * 2.5;
cout << a << " * " << 2.5 << " = " << an << '\n';
auto nb = 1.5 * b;
cout << 1.5 << " * " << b << " = " << nb << '\n';
cout << "Length A: " << a.length() << '\n';
cout << "Length B: " << b.length() << '\n';
if (a == b) cout << "A == B\n";
if (a != b) cout << "A != B\n";
if (a < b) cout << "A < B\n";
if (a > b) cout << "A > B\n";
if (a <= b) cout << "A <= B\n";
if (a >= b) cout << "A >= B\n";
if (b == a) cout << "B == A\n";
if (b != a) cout << "B != A\n";
if (b < a) cout << "B < A\n";
if (b > a) cout << "B > A\n";
if (b <= a) cout << "B <= A\n";
if (b >= a) cout << "B >= A\n";
system("pause > nul");
}