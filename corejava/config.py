from pathlib import Path

# 项目根目录
ROOT_DIR = Path(__file__).resolve().parent.parent

# 输出目录
OUT_DIR = ROOT_DIR / 'out'

# 构建目标配置文件
TARGETS_CONFIG_FILE = ROOT_DIR / 'targets.json'

# 测试用例配置文件
TESTS_CONFIG_FILE = ROOT_DIR / 'tests.json'
