from collections import defaultdict, deque

class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def add_edge(self, u, v):
        self.graph[u].append(v)

    def dfs(self, start, order='left'):
        visited, stack, result = set(), [start], []

        while stack:
            node = stack.pop()
            if node not in visited:
                visited.add(node)
                result.append(node)
                neighbors = sorted(self.graph[node])
                stack.extend(neighbors if order == 'left' else reversed(neighbors))

        return result

    def bfs(self, start, order='left'):
        visited, queue, result = set(), deque([start]), []

        while queue:
            node = queue.popleft()
            if node not in visited:
                visited.add(node)
                result.append(node)
                neighbors = sorted(self.graph[node])
                queue.extend(neighbors if order == 'left' else reversed(neighbors))

        return result

    def detect_cycles(self):
        visited, stack, cycles = set(), set(), []

        def dfs_cycle(node, current_cycle):
            visited.add(node)
            stack.add(node)

            for neighbor in self.graph[node]:
                if neighbor not in visited:
                    if dfs_cycle(neighbor, current_cycle + [neighbor]):
                        return True
                elif neighbor in stack:
                    cycles.append(current_cycle + [neighbor])
                    return True

            stack.remove(node)
            return False

        for node in self.graph:
            if node not in visited:
                dfs_cycle(node, [node])

        return cycles

    def is_bipartite(self):
        color, visited = {}, set()

        def bfs_color(node):
            queue = deque([(node, 0)])

            while queue:
                current, current_color = queue.popleft()
                if current in visited:
                    continue

                visited.add(current)

                if current not in color:
                    color[current] = current_color

                for neighbor in self.graph[current]:
                    if neighbor not in color:
                        queue.append((neighbor, 1 - current_color))
                    elif color[neighbor] == current_color:
                        return False

            return True

        return all(bfs_color(node) for node in self.graph)

# Example usage
graph = Graph()
edges = [(1, 3), (1, 4), (2, 1), (2, 3), (3, 4), (4, 1), (4, 2)]

for edge in edges:
    graph.add_edge(edge[0], edge[1])

print("DFS:", graph.dfs(1))
print("BFS:", graph.bfs(1))
cycles = graph.detect_cycles()
print("Cycles present:", cycles if cycles else "No cycles found")
print("Is Bipartite:", graph.is_bipartite())
