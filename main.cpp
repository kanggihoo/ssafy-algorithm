#include <vector>
#include <iostream>
#include <string>
#include <sstream>
#include <unordered_map>
#include <algorithm>
#include <map>
#include <cmath>
#include <queue>
using namespace std;

// 트럭이 정해진 순서로 다리 건너기, 모든 트럭 건너는데에 최소 몇초 
// 다리에는 중량 제한 , 최대 올라갈수 있는 차량 제한 


int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0;
    int q_weight;
    int weight_idx = 0;
    int N = truck_weights.size();
    // 처음 큐 초기화 pair<w , t> 
    queue<pair<int , int>> q;



    // 큐의 원소가 개수가 bridge_length 보다 (트럭이 나와야 되는 시간과 트럭 무게를 넣고)
    while(!q.empty()){
        auto& [w,t] = q.front();
        answer = t;
        q.pop();


        // 큐에 넣을 수 있을떄 까지 넣기 
        int tmp =t;
        while(weight_idx < N && q.size()+1 <= bridge_length && truck_weights[weight_idx]+q_weight <= weight){
            q.push({truck_weights[weight_idx] , tmp+q.size()});
            q_weight += truck_weights[weight_idx];
            weight_idx++;
            tmp++;
        }
        
    }

    return answer;
}

int main(){
    
    // solution(vector({6, 10, 2}));
    
    return 0;
}