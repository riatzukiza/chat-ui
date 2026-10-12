// Read-only observation of the unchanged configured compiled test bundle.
// Do not modify source or its generated artifacts; preserve the original exit request.
const path = require('node:path');
const realExit = process.exit.bind(process);
let requestedExit = null;
let rejections = [];
let finished = false;
const timeout = setTimeout(() => {
  if (!finished) { console.error('OBSERVER_TIMEOUT'); realExit(2); }
}, 6000);
// A real backend call is forbidden. The existing test replaces this with its own mock.
global.fetch = () => Promise.reject(new Error('UNEXPECTED_REAL_FETCH_FORBIDDEN'));
process.on('unhandledRejection', error => {
  const observed = {name:error?.name,message:error?.message,stack:error?.stack};
  rejections.push(observed);
  console.error('OBSERVED_UNHANDLED_TEST_REJECTION '+JSON.stringify(observed));
});
process.exit = code => {
  requestedExit = code;
  console.log('ORIGINAL_RUNNER_EXIT_REQUEST '+code);
  setTimeout(() => {
    finished = true; clearTimeout(timeout);
    console.log('OBSERVATION_RESULT '+JSON.stringify({originalRunnerExit:requestedExit,unhandledRejections:rejections.length,sourceAndCompiledFilesModified:false}));
    realExit(rejections.length ? 1 : code);
  }, 250);
};
require(path.resolve(__dirname,'reproduction-worktree/target/test.cjs'));
