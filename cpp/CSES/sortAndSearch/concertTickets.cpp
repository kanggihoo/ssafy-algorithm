#include <iostream>
#include <set>
#include <utility>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, M;
    cin >> N >> M;

    set<pair<int, int>> tickets;
    for (int i = 0; i < N; i++) {
        int price;
        cin >> price;
        tickets.insert({price, i});
    }

    for (int i = 0; i < M; i++) {
        int maxPrice;
        cin >> maxPrice;

        auto it = tickets.upper_bound({maxPrice, N});
        if (it == tickets.begin()) {
            cout << -1 << "\n";
            continue;
        }

        --it;
        cout << it->first << "\n";
        tickets.erase(it);
    }

    return 0;
}
